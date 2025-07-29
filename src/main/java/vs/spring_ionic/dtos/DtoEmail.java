package vs.spring_ionic.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class DtoEmail implements Serializable
{
   @NotEmpty(message = "Preenchimento obrigatório!")
   @Email(message = "E-mail inválido!")
   private String email;

   public DtoEmail() {}

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }
}
