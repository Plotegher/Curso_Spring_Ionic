package vs.spring_ionic.utilidades;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vs.spring_ionic.dtos.DtoCredenciais;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtFiltroAutenticacao extends UsernamePasswordAuthenticationFilter
{
   private final AuthenticationManager authenticationManager;
   private final JwtUtil jwtUtil;

   public JwtFiltroAutenticacao(AuthenticationManager authenticationManager, JwtUtil jwtUtil)
   {
      setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
      this.authenticationManager = authenticationManager;
      this.jwtUtil = jwtUtil;
   }

   @Override
   public Authentication attemptAuthentication(HttpServletRequest requisicao, HttpServletResponse resposta)
         throws AuthenticationException
   {
      try
      {
         DtoCredenciais credenciais = new ObjectMapper()
               .readValue(requisicao.getInputStream(), DtoCredenciais.class);
         UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
               (credenciais.getEmail(), credenciais.getSenha(), new ArrayList<>());
         return authenticationManager.authenticate(authToken);
//         Authentication auth = authenticationManager.authenticate(authToken);
//         return auth; verificar se pode tirar esse se o programa rodar
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }

   @Override
   protected void successfulAuthentication(HttpServletRequest requisicao, HttpServletResponse resposta,
         FilterChain filter, Authentication auth) throws IOException, ServletException
   {
      String usuario = ((UsuarioSpringSecurity) auth.getPrincipal()).getUsername();
      String token = jwtUtil.geraToken(usuario);
      resposta.addHeader("Authorization", "Bearer " + token);
   }

   private static class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler
   {
      @Override
      public void onAuthenticationFailure(HttpServletRequest requisicao, HttpServletResponse resposta,
            AuthenticationException excecao) throws IOException, ServletException
      {
         resposta.setStatus(401);
         resposta.setContentType("application/json");
         resposta.getWriter().append(json());
      }

      private String json() {
         long date = new Date().getTime();
         return "{\"timestamp\": " + date + ", "
               + "\"status\": 401, "
               + "\"error\": \"Não autorizado\", "
               + "\"message\": \"Email ou senha inválidos\", "
               + "\"path\": \"/login\"}";
      }
   }
}
