package vs.spring_ionic.dtos;

import java.io.Serializable;

public class DtoCredenciais implements Serializable
{
   private String email;
   private String senha;

   public DtoCredenciais() {}

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getSenha()
   {
      return senha;
   }

   public void setSenha(String senha)
   {
      this.senha = senha;
   }
}
