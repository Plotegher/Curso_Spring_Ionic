package vs.spring_ionic.utilidades;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vs.spring_ionic.entidades.Perfil;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioSpringSecurity implements UserDetails
{
   private Integer id;
   private String email;
   private String senha;
   private Collection<? extends GrantedAuthority> authorities;

   public UsuarioSpringSecurity() {}

   public UsuarioSpringSecurity(Integer id, String email, String senha, Set<Perfil> perfis)
   {
      this.id = id;
      this.email = email;
      this.senha = senha;
      this.authorities = perfis.stream().map(x ->
            new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
   }

   public Integer getId()
   {
      return id;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities()
   {
      return authorities;
   }

   @Override
   public String getPassword()
   {
      return senha;
   }

   @Override
   public String getUsername()
   {
      return email;
   }

   @Override
   public boolean isAccountNonExpired()
   {
      //return UserDetails.super.isAccountNonExpired();
      return true;
   }

   @Override
   public boolean isAccountNonLocked()
   {
      //return UserDetails.super.isAccountNonLocked();
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired()
   {
      //return UserDetails.super.isCredentialsNonExpired();
      return true;
   }

   @Override
   public boolean isEnabled()
   {
      //return UserDetails.super.isEnabled();
      return true;
   }
}
