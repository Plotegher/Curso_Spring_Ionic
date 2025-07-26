package vs.spring_ionic.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.servicos.AtualizarCliente;

import java.io.Serializable;

@AtualizarCliente
public class DtoCliente implements Serializable
{
   private Integer id;
   @NotEmpty(message = "Preenchimento obrigatório!")
   @Length(min = 3, max = 120, message = "Nome entre 3 e 80 caracteres!")
   private String nome;
   @NotEmpty(message = "Preenchimento obrigatório!")
   @Email(message = "E-mail inválido!")
   private String email;

   public DtoCliente() {}

   public DtoCliente(Cliente obj)
   {
      id = obj.getId();
      nome = obj.getNome();
      email = obj.getEmail();
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }
}
