package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.Categoria;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioCategoria;

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
}
