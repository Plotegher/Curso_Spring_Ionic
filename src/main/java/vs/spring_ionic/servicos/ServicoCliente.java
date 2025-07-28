package vs.spring_ionic.servicos;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vs.spring_ionic.dtos.DtoCliente;
import vs.spring_ionic.dtos.DtoClienteNovo;
import vs.spring_ionic.entidades.*;
import vs.spring_ionic.excecoes.ExcecaoAuthorization;
import vs.spring_ionic.excecoes.ExcecaoDataIntegrity;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioCliente;
import vs.spring_ionic.repositorios.RepositorioEndereco;
import vs.spring_ionic.utilidades.UsuarioSpringSecurity;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoCliente
{
   @Autowired
   private RepositorioCliente repositorioCliente;

   @Autowired
   private RepositorioEndereco repositorioEndereco;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   public Cliente buscar(Integer id)
   {
      UsuarioSpringSecurity usuario = ServicoUsuario.autenticado();
      if (usuario == null || !usuario.hasRole(Perfil.ADMIN) && !id.equals(usuario.getId()))
      {
         throw  new ExcecaoAuthorization("Acesso negado!");
      }

      Optional<Cliente> obj = repositorioCliente.findById(id);
      return obj.orElseThrow(() -> new ExcecaoObjectNotFound
            ("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
   }

   public List<Cliente> buscarTudo()
   {
      return repositorioCliente.findAll();
   }

   public Page<Cliente> buscarPagina(Integer pagina, Integer linhasPorPagina,
                                       String direcao, String ordenadoPor)
   {
      PageRequest pageRequest = PageRequest.of(pagina,
            linhasPorPagina,
            Sort.Direction.valueOf(direcao),
            ordenadoPor);
      return repositorioCliente.findAll(pageRequest);
   }

   // Método auxiliar para converter um objeto DtoCliente
   // em um objeto Cliente
   public Cliente origemDto(DtoCliente objDto)
   {
      return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
   }

   // Método auxiliar sobrecarregado para converter um objeto DtoClienteNovo
   // em um objeto Cliente
   public Cliente origemDto(DtoClienteNovo objDto)
   {
      Cliente cliente = new Cliente(null,
            objDto.getNome(),
            objDto.getEmail(),
            objDto.getCpfCnpj(),
            TipoCliente.converteParaEnum(objDto.getTipoCliente()),
            bCryptPasswordEncoder.encode(objDto.getSenha()));

      Municipio municipio = new Municipio(objDto.getMunicipioId(),
            null,
            null);

      Endereco end = new Endereco(null,
            objDto.getLogradouro(),
            objDto.getNumero(),
            objDto.getComplemento(),
            objDto.getBairro(),
            objDto.getCep(),
            cliente,
            municipio);

      cliente.getEnderecos().add(end);

      cliente.getTelefones().add(objDto.getTelefone1());
      if (objDto.getTelefone2()!=null)
      {
         cliente.getTelefones().add(objDto.getTelefone2());
      }
      if (objDto.getTelefone3()!=null)
      {
         cliente.getTelefones().add(objDto.getTelefone3());
      }
      return cliente;
   }

   @Transactional
   public Cliente incluir(Cliente obj)
   {
      obj.setId(null);
      obj = repositorioCliente.save(obj);
      repositorioEndereco.saveAll(obj.getEnderecos());
      return obj;
   }

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
      return repositorioCliente.save(novoObj);
   }

   public void excluir(Integer id)
   {
      buscar(id);
      try
      {
         repositorioCliente.deleteById(id);
      }
      catch (DataIntegrityViolationException e)
      {
         throw new ExcecaoDataIntegrity
               ("Não é possível excluir um cliente que possui pedido(s) associado(s)!");
      }
   }
}
