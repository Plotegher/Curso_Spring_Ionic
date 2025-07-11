package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioCliente;

import java.util.Optional;

@Service
public class ServicoCliente
{
   @Autowired
   private RepositorioCliente repositorio;

   public Cliente buscar(Integer id)
   {
      Optional<Cliente> obj = repositorio.findById(id);
      return obj.orElseThrow(() -> new ExcecaoObjectNotFound
            ("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
   }
}
