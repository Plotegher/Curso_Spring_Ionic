package vs.spring_ionic.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vs.spring_ionic.entidades.Categoria;
import vs.spring_ionic.servicos.ServicoCategoria;

@RestController
@RequestMapping(value = "/categorias")
public class RecursoCategoria
{
   @Autowired
   private ServicoCategoria servico;

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<?> buscar(@PathVariable Integer id)
   {
      Categoria obj = servico.buscar(id);
      return ResponseEntity.ok().body(obj);
   }
}
