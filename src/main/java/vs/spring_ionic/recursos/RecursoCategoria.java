package vs.spring_ionic.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vs.spring_ionic.entidades.Categoria;
import vs.spring_ionic.servicos.ServicoCategoria;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class RecursoCategoria
{
   @Autowired
   private ServicoCategoria servico;

   // Buscar uma categoria
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Categoria> buscar(@PathVariable Integer id)
   {
      Categoria obj = servico.buscar(id);
      return ResponseEntity.ok().body(obj);
   }

   // Incluir uma categoria
   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> incluir(@RequestBody Categoria obj)
   {
      obj = servico.incluir(obj);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   // Atualizar uma categoria
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> atualizar(@RequestBody Categoria obj, @PathVariable Integer id)
   {
      obj.setId(id);
      obj = servico.atualizar(obj);
      return ResponseEntity.noContent().build();
   }

   // Excluir uma categoria
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> excluir(@PathVariable Integer id)
   {
      servico.excluir(id);
      return ResponseEntity.noContent().build();
   }
}
