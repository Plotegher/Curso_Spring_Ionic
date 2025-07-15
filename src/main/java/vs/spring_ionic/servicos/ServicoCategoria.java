package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vs.spring_ionic.dto.DtoCategoria;
import vs.spring_ionic.entidades.Categoria;
import vs.spring_ionic.excecoes.ExcecaoDataIntegrity;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioCategoria;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoCategoria
{
   @Autowired
   private RepositorioCategoria repositorio;

   public Categoria buscar(Integer id)
   {
      Optional<Categoria> obj = repositorio.findById(id);
      return obj.orElseThrow(() -> new ExcecaoObjectNotFound
            ("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
   }

   public List<Categoria> buscarTudo()
   {
      return repositorio.findAll();
   }

   public Page<Categoria> buscarPagina(Integer pagina, Integer linhasPorPagina,
                                       String direcao, String ordenadoPor)
   {
      PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), ordenadoPor);
      return repositorio.findAll(pageRequest);
   }

   // Método auxiliar para converter um objeto DtoCategoria em
   // um objeto Categoria
   public Categoria origemDto(DtoCategoria objDto)
   {
      return new Categoria(objDto.getId(), objDto.getNome());
   }

   public Categoria incluir(Categoria obj)
   {
      obj.setId(null);
      return repositorio.save(obj);
   }

   public Categoria atualizar(Categoria obj)
   {
      buscar(obj.getId());
      return repositorio.save(obj);
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
               ("Não é possível excluir uma categoria que possui produto(s) associado(s)!");
      }
   }
}
