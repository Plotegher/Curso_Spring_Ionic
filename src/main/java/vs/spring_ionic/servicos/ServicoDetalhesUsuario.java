package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.repositorios.RepositorioCliente;
import vs.spring_ionic.utilidades.UsuarioSpringSecurity;

@Service
public class ServicoDetalhesUsuario implements UserDetailsService
{
  @Autowired
  private RepositorioCliente repositorioCliente;

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
   {
      Cliente cliente = repositorioCliente.findByEmail(email);
      if (cliente == null)
      {
         throw new UsernameNotFoundException(email);
      }
      return new UsuarioSpringSecurity
      (
         cliente.getId(),
         cliente.getEmail(),
         cliente.getSenha(),
         cliente.getPerfis()
      );
   }
}
