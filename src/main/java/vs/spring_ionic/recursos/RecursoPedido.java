package vs.spring_ionic.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vs.spring_ionic.entidades.Pedido;
import vs.spring_ionic.servicos.ServicoPedido;

@RestController
@RequestMapping(value = "/pedidos")
public class RecursoPedido
{
   @Autowired
   private ServicoPedido servico;

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Pedido> buscar(@PathVariable Integer id)
   {
      Pedido obj = servico.buscar(id);
      return ResponseEntity.ok().body(obj);
   }
}
