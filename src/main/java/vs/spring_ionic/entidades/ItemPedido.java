package vs.spring_ionic.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable
{
   @EmbeddedId
   private ItemPedidoPK id = new ItemPedidoPK();

   private Double desconto;
   private Integer quantidade;
   private Double preco;

   public ItemPedido() {}

   public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco)
   {
      id.setPedido(pedido);
      id.setProduto(produto);
      this.desconto = desconto;
      this.quantidade = quantidade;
      this.preco = preco;
   }

   public ItemPedidoPK getId()
   {
      return id;
   }

   public void setId(ItemPedidoPK id)
   {
      this.id = id;
   }

   public Double getDesconto()
   {
      return desconto;
   }

   public void setDesconto(Double desconto)
   {
      this.desconto = desconto;
   }

   public Integer getQuantidade()
   {
      return quantidade;
   }

   public void setQuantidade(Integer quantidade)
   {
      this.quantidade = quantidade;
   }

   public Double getPreco()
   {
      return preco;
   }

   public void setPreco(Double preco)
   {
      this.preco = preco;
   }

   @JsonIgnore
   public Pedido getPedido()
   {
      return id.getPedido();
   }

   public Produto getProduto()
   {
      return id.getProduto();
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null || getClass() != o.getClass()) return false;
      ItemPedido that = (ItemPedido) o;
      return Objects.equals(id, that.id);
   }

   @Override
   public int hashCode()
   {
      return Objects.hashCode(id);
   }
}
