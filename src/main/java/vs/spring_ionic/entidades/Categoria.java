package vs.spring_ionic.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Categoria implements Serializable
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nome;

   @ManyToMany(mappedBy = "categorias")
   private List<Produto> produtos = new ArrayList<>();

   public Categoria() {}

   public Categoria(Integer id, String nome)
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

   public List<Produto> getProdutos()
   {
      return produtos;
   }

   public void setProdutos(List<Produto> produtos)
   {
      this.produtos = produtos;
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null || getClass() != o.getClass()) return false;
      Categoria categoria = (Categoria) o;
      return Objects.equals(id, categoria.id);
   }

   @Override
   public int hashCode()
   {
      return Objects.hashCode(id);
   }
}
