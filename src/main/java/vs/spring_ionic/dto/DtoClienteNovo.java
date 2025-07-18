package vs.spring_ionic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import vs.spring_ionic.servicos.IncluirCliente;

import java.io.Serializable;

@IncluirCliente
public class DtoClienteNovo implements Serializable
{
   @NotEmpty(message = "Preenchimento obrigatório!")
   @Length(min = 3, max = 120, message = "Nome entre 3 e 80 caracteres!")
   private String nome;
   @NotEmpty(message = "Preenchimento obrigatório!")
   @Email(message = "E-mail inválido!")
   private String email;
   private String cpfCnpj;
   private Integer tipoCliente;

   @NotEmpty(message = "Preenchimento obrigatório!")
   private String logradouro;
   @NotEmpty(message = "Preenchimento obrigatório!")
   private String numero;
   private String complemento;
   private String bairro;
   @NotEmpty(message = "Preenchimento obrigatório!")
   private String cep;

   @NotEmpty(message = "Preenchimento obrigatório!")
   private String telefone1;
   private String telefone2;
   private String telefone3;

   private Integer municipioId;

   public DtoClienteNovo() {}

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

   public String getCpfCnpj()
   {
      return cpfCnpj;
   }

   public void setCpfCnpj(String cpfCnpj)
   {
      this.cpfCnpj = cpfCnpj;
   }

   public Integer getTipoCliente()
   {
      return tipoCliente;
   }

   public void setTipoCliente(Integer tipoCliente)
   {
      this.tipoCliente = tipoCliente;
   }

   public String getLogradouro()
   {
      return logradouro;
   }

   public void setLogradouro(String logradouro)
   {
      this.logradouro = logradouro;
   }

   public String getNumero()
   {
      return numero;
   }

   public void setNumero(String numero)
   {
      this.numero = numero;
   }

   public String getComplemento()
   {
      return complemento;
   }

   public void setComplemento(String complemento)
   {
      this.complemento = complemento;
   }

   public String getBairro()
   {
      return bairro;
   }

   public void setBairro(String bairro)
   {
      this.bairro = bairro;
   }

   public String getCep()
   {
      return cep;
   }

   public void setCep(String cep)
   {
      this.cep = cep;
   }

   public String getTelefone1()
   {
      return telefone1;
   }

   public void setTelefone1(String telefone1)
   {
      this.telefone1 = telefone1;
   }

   public String getTelefone2()
   {
      return telefone2;
   }

   public void setTelefone2(String telefone2)
   {
      this.telefone2 = telefone2;
   }

   public String getTelefone3()
   {
      return telefone3;
   }

   public void setTelefone3(String telefone3)
   {
      this.telefone3 = telefone3;
   }

   public Integer getMunicipioId()
   {
      return municipioId;
   }

   public void setMunicipioId(Integer municipioId)
   {
      this.municipioId = municipioId;
   }
}
