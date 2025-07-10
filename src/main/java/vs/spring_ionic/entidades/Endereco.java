package vs.spring_ionic.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Endereco implements Serializable
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String logradouro;
   private String numero;
   private String complemento;
   private String bairro;
   private String cep;

   @ManyToOne
   @JoinColumn(name = "cliente_id")
   private Cliente cliente;
   @ManyToOne
   @JoinColumn(name = "cidade_id")
   private Municipio municipio;

   public Endereco(Integer id, String logradouro, String numero, String complemento,
          String bairro, String cep, Cliente cliente, Municipio municipio)
   {
      this.id = id;
      this.logradouro = logradouro;
      this.numero = numero;
      this.complemento = complemento;
      this.bairro = bairro;
      this.cep = cep;
      this.cliente = cliente;
      this.municipio = municipio;
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
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

   public Cliente getCliente()
   {
      return cliente;
   }

   public void setCliente(Cliente cliente)
   {
      this.cliente = cliente;
   }

   public Municipio getMunicipio()
   {
      return municipio;
   }

   public void setMunicipio(Municipio municipio)
   {
      this.municipio = municipio;
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null || getClass() != o.getClass()) return false;
      Endereco endereco = (Endereco) o;
      return Objects.equals(id, endereco.id);
   }

   @Override
   public int hashCode()
   {
      return Objects.hashCode(id);
   }
}
