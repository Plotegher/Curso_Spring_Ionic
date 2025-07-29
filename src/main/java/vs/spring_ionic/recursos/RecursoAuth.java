package vs.spring_ionic.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vs.spring_ionic.servicos.ServicoUsuario;
import vs.spring_ionic.utilidades.JwtUtil;
import vs.spring_ionic.utilidades.UsuarioSpringSecurity;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class RecursoAuth
{
   @Autowired
   private JwtUtil jwtUtil;

   @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
   public ResponseEntity<Void> renovaToken(HttpServletResponse reposta)
   {
      UsuarioSpringSecurity usuarioSS = ServicoUsuario.autenticado();
      assert usuarioSS != null;
      String token = jwtUtil.geraToken(usuarioSS.getUsername());
      reposta.addHeader("Authorization", "Bearer " + token);
      return ResponseEntity.noContent().build();
   }
}
