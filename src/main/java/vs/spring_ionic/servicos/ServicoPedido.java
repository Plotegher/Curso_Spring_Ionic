package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.Pedido;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioPedido;

import java.util.Optional;

@Service
public class ServicoPedido
{
   @Autowired
   private RepositorioPedido repositorio;

   public Pedido buscar(Integer id)
   {
      Optional<Pedido> obj = repositorio.findById(id);
      return obj.orElseThrow(() -> new ExcecaoObjectNotFound
            ("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
   }
}
