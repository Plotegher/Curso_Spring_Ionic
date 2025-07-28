package vs.spring_ionic.utilidades;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil
{
   @Value("${jwt.secret}")
   private String secret;

   @Value("${jwt.expiration}")
   private Long expiration;

   public String geraToken(String usuario)
   {
      return Jwts.builder()
            .setSubject(usuario)
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.getBytes())
            .compact();
   }

   public boolean tokenValido(String token)
   {
      Claims claims = getClaims(token);
      if (claims != null)
      {
         String nomeUsuario = claims.getSubject();
         Date dataExpiracao = claims.getExpiration();
         Date agora = new Date(System.currentTimeMillis());
         if (nomeUsuario != null && dataExpiracao != null && agora.before(dataExpiracao))
         {
            return true;
         }
      }
      return false;
   }

   public String getNomeUsuario(String token)
   {
      Claims claims = getClaims(token);
      if (claims != null)
      {
         return claims.getSubject();
      }
      return null;
   }

   private Claims getClaims(String token)
   {
      try
      {
         return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
      }
      catch (Exception e)
      {
         return null;
      }
   }
}
