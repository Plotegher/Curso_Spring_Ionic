package vs.spring_ionic.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Estado implements Serializable
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nome;

   @JsonIgnore // // Estado não pode serializar o seu Municipio
   @OneToMany(mappedBy = "estado")
   private List<Municipio> municipios = new ArrayList<>();

   public Estado() {}

   public Estado(Integer id, String nome)
   {
      this.id = id;
      this.nome = nome;
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

   public List<Municipio> getMunicipios()
   {
      return municipios;
   }

   public void setMunicipios(List<Municipio> municipios)
   {
      this.municipios = municipios;
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null || getClass() != o.getClass()) return false;
      Estado estado = (Estado) o;
      return Objects.equals(id, estado.id);
   }

   @Override
   public int hashCode()
   {
      return Objects.hashCode(id);
   }
}
