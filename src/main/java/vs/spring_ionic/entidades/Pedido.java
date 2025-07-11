package vs.spring_ionic.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Pedido implements Serializable
{
   private Integer id;
   private Date instante;

   private Pagamento pagamento;
   private Cliente cliente;
   private Endereco enderecoEntrega;

   public Pedido() {}

   public Pedido(Integer id, Date instante, Pagamento pagamento, Cliente cliente, Endereco enderecoEntrega)
   {
      this.id = id;
      this.instante = instante;
      this.pagamento = pagamento;
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
