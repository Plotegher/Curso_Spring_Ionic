package vs.spring_ionic.dto;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import vs.spring_ionic.entidades.Categoria;

import java.io.Serializable;

public class DtoCategoria implements Serializable
{
   private Integer id;
   @NotEmpty(message = "Preenchimento obrigatório")
   @Length(min = 3, max = 80, message = "Nome entre 3 e 80 caracteres")
   private String nome;

   public DtoCategoria() {}

   public DtoCategoria(Categoria obj)
   {
      id = obj.getId();
      nome = obj.getNome();
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
}
