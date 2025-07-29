package vs.spring_ionic.recursos;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vs.spring_ionic.entidades.Pedido;
import vs.spring_ionic.servicos.ServicoPedido;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class RecursoPedido
{
   @Autowired
   private ServicoPedido servico;

   // Buscar um pedido com base no id
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Pedido> buscar(@PathVariable Integer id)
   {
      Pedido obj = servico.buscar(id);
      return ResponseEntity.ok().body(obj);
   }

   // Buscar pedidos por página
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<Page<Pedido>> buscarPagina
   (@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
    @RequestParam(value = "linhasPorPagina", defaultValue = "24")Integer linhasPorPagina,
    @RequestParam(value = "direcao", defaultValue = "DESC")String direcao,
    @RequestParam(value = "ordenadoPor", defaultValue = "instante")String ordenadoPor)
   {
      Page<Pedido> lista = servico.buscarPagina(pagina, linhasPorPagina, direcao, ordenadoPor);
      return ResponseEntity.ok().body(lista);
   }

   // Incluir um pedido
   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> incluir(@Valid @RequestBody Pedido obj)
   {
      obj = servico.incluir(obj);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }
}
