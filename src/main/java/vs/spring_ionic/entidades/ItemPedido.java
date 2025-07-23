package vs.spring_ionic.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
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

   public void setPedido(Pedido pedido)
   {
      id.setPedido(pedido);
   }

   public Produto getProduto()
   {
      return id.getProduto();
   }

   public void setProduto(Produto produto)
   {
      id.setProduto(produto);
   }

   public double getSubTotal()
   {
      return (preco - desconto) * quantidade;
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

   @Override
   public String toString()
   {
      NumberFormat nft = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
      final StringBuilder sbd = new StringBuilder("Item [");
      sbd.append(getProduto().getNome());
      sbd.append(", Quantidade = ");
      sbd.append(getQuantidade());
      sbd.append(", Preço = ");
      sbd.append(nft.format(getPreco()));
      sbd.append(", Sub-total = ");
      sbd.append(nft.format(getSubTotal()));
      sbd.append(']');
      sbd.append("\n");
      return sbd.toString();
   }
}
