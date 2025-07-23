package vs.spring_ionic.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Pedido implements Serializable
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
   private Date instante;

   // Mapeamento bidirecional 1 para 1
   @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
   private Pagamento pagamento;

   @ManyToOne
   @JoinColumn(name = "cliente_id")
   private Cliente cliente;

   @ManyToOne
   @JoinColumn(name = "endereco_entrega_id")
   private Endereco enderecoEntrega;

   // Os itens serão serializados porque são uma coleção
   @OneToMany(mappedBy = "id.pedido")
   private Set<ItemPedido> itens = new HashSet<>();

   public Pedido() {}

   public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoEntrega)
   {
      this.id = id;
      this.instante = instante;
      this.cliente = cliente;
      this.enderecoEntrega = enderecoEntrega;
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public Date getInstante()
   {
      return instante;
   }

   public void setInstante(Date instante)
   {
      this.instante = instante;
   }

   public Pagamento getPagamento()
   {
      return pagamento;
   }

   public void setPagamento(Pagamento pagamento)
   {
      this.pagamento = pagamento;
   }

   public Cliente getCliente()
   {
      return cliente;
   }

   public void setCliente(Cliente cliente)
   {
      this.cliente = cliente;
   }

   public Endereco getEnderecoEntrega()
   {
      return enderecoEntrega;
   }

   public void setEnderecoEntrega(Endereco enderecoEntrega)
   {
      this.enderecoEntrega = enderecoEntrega;
   }

   public Set<ItemPedido> getItens()
   {
      return itens;
   }

   public void setItens(Set<ItemPedido> itens)
   {
      this.itens = itens;
   }

   public double getValorTotal()
   {
      double soma = 0.0;
      for (ItemPedido item : itens)
      {
         soma += item.getSubTotal();
      }
      return soma;
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null || getClass() != o.getClass()) return false;
      Pedido pedido = (Pedido) o;
      return Objects.equals(id, pedido.id);
   }

   @Override
   public int hashCode()
   {
      return Objects.hashCode(id);
   }

   @Override
   public String toString()
   {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      NumberFormat nft = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
      final StringBuilder sbd = new StringBuilder();
      sbd.append("\nPedido n°: ");
      sbd.append(getId());
      sbd.append(", Instante = ");
      sbd.append(sdf.format(getInstante()));
      sbd.append(", Cliente: ");
      sbd.append(getCliente().getNome());
      sbd.append(", Pagamento: ");
      sbd.append(getPagamento().getEstado().getDescricao());
      sbd.append("\n");
      sbd.append("\nDetalhes:\n");
      for (ItemPedido item: getItens())
      {
         sbd.append(item.toString());
      }
      sbd.append("Valor total: ");
      sbd.append(nft.format(getValorTotal()));
      sbd.append("\n");
      return sbd.toString();
   }
}
