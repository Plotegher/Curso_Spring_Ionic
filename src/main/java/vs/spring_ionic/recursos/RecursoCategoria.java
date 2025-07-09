package vs.spring_ionic.recursos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vs.spring_ionic.entidades.Categoria;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class RecursoCategoria
{
   @RequestMapping(method = RequestMethod.GET)
   public List<Categoria> listar()
   {
      Categoria cat1 = new Categoria(1, "Informática");
      Categoria cat2 = new Categoria(2, "Escritório");

      List<Categoria> lista = new ArrayList<>();
      lista.add(cat1);
      lista.add(cat2);

      return lista;
   }
}
