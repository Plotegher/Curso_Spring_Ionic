package vs.spring_ionic.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vs.spring_ionic.dtos.DtoEmail;
import vs.spring_ionic.servicos.ServicoAuth;
import vs.spring_ionic.servicos.ServicoUsuario;
import vs.spring_ionic.utilidades.JwtUtil;
import vs.spring_ionic.utilidades.UsuarioSpringSecurity;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class RecursoAuth
{
   @Autowired
   private JwtUtil jwtUtil;

   @Autowired
   private ServicoAuth servicoAuth;

   @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
   public ResponseEntity<Void> renovaToken(HttpServletResponse reposta)
   {
      UsuarioSpringSecurity usuarioSS = ServicoUsuario.autenticado();
      assert usuarioSS != null;
      String token = jwtUtil.geraToken(usuarioSS.getUsername());
      reposta.addHeader("Authorization", "Bearer " + token);
      return ResponseEntity.noContent().build();
   }

   @RequestMapping(value = "/forgot", method = RequestMethod.POST)
   public ResponseEntity<Void> esqueciMinhaSenha(@Valid @RequestBody DtoEmail objDto)
   {
      servicoAuth.envieNovaSenha(objDto.getEmail());
      return ResponseEntity.noContent().build();
   }
}
