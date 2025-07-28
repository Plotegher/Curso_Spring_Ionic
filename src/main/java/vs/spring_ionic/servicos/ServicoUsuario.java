package vs.spring_ionic.servicos;

import org.springframework.security.core.context.SecurityContextHolder;
import vs.spring_ionic.utilidades.UsuarioSpringSecurity;

public class ServicoUsuario
{
   public static UsuarioSpringSecurity autenticado()
   {
      try
      {
         return (UsuarioSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      }
      catch (Exception e)
      {
         return null;
      }
   }
}
