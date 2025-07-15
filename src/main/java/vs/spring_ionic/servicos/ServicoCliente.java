package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vs.spring_ionic.dto.DtoCliente;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.excecoes.ExcecaoDataIntegrity;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioCliente;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoCliente
{
   @Autowired
   private RepositorioCliente repositorio;

   public Cliente buscar(Integer id)
   {
      Optional<Cliente> obj = repositorio.findById(id);
      return obj.orElseThrow(() -> new ExcecaoObjectNotFound
            ("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
   }

   public List<Cliente> buscarTudo()
   {
      return repositorio.findAll();
   }

   public Page<Cliente> buscarPagina(Integer pagina, Integer linhasPorPagina,
                                       String direcao, String ordenadoPor)
   {
      PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), ordenadoPor);
      return repositorio.findAll(pageRequest);
   }

   // Método auxiliar para converter um objeto DtoCliente
   // em um objeto Cliente
   public Cliente origemDto(DtoCliente objDto)
   {
      return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
   }

   // Incluir

   // Método auxiliar para atualizar os dados de um novo objeto (novoObj)
   // buscado no BD, com base no objeto que veio como argumento (obj)
   private void atualizaDados(Cliente novoObj, Cliente obj)
   {
      novoObj.setNome(obj.getNome());
      novoObj.setEmail(obj.getEmail());
   }

   public Cliente atualizar(Cliente obj)
   {
      Cliente novoObj = buscar(obj.getId());
      atualizaDados(novoObj, obj);
      return repositorio.save(novoObj);
   }

   public void excluir(Integer id)
   {
      buscar(id);
      try
      {
         repositorio.deleteById(id);
      }
      catch (DataIntegrityViolationException e)
      {
         throw new ExcecaoDataIntegrity
               ("Não é possível excluir um cliente que possui pedido(s) associado(s)!");
      }
   }
}
