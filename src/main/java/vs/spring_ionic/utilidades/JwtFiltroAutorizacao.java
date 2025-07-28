package vs.spring_ionic.utilidades;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import vs.spring_ionic.servicos.ServicoDetalhesUsuario;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFiltroAutorizacao extends BasicAuthenticationFilter
{
   private final JwtUtil jwtUtil;

   private final ServicoDetalhesUsuario servicoDetalhesUsuario;

   public JwtFiltroAutorizacao(AuthenticationManager authenticationManager,
         JwtUtil jwtUtil, ServicoDetalhesUsuario servicoDetalhesUsuario)
   {
      super(authenticationManager);
      this.jwtUtil = jwtUtil;
      this.servicoDetalhesUsuario = servicoDetalhesUsuario;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest requisicao,
         HttpServletResponse reposta, FilterChain filtro) throws IOException, ServletException
   {
      String header = requisicao.getHeader("Authorization");
      if (header != null && header.startsWith("Bearer "))
      {
         UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
         if (auth != null)
         {
            SecurityContextHolder.getContext().setAuthentication(auth);
         }
      }
      filtro.doFilter(requisicao, reposta);
   }

   private UsernamePasswordAuthenticationToken getAuthentication(String token)
   {
      if (jwtUtil.tokenValido(token))
      {
         String nomeUsuario = jwtUtil.getNomeUsuario(token);
         UserDetails usuario = servicoDetalhesUsuario.loadUserByUsername(nomeUsuario);
         return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
      }
      return null;
   }
}
