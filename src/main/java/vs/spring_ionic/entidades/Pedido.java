package vs.spring_ionic.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
}
