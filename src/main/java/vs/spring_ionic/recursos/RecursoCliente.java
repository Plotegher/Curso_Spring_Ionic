package vs.spring_ionic.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.servicos.ServicoCliente;

@RestController
@RequestMapping(value = "/clientes")
public class RecursoCliente
{
   @Autowired
   private ServicoCliente servico;

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Cliente> buscar(@PathVariable Integer id)
   {
      Cliente obj = servico.buscar(id);
      return ResponseEntity.ok().body(obj);
   }
}
