package vs.spring_ionic.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable
{
   @Id
   private Integer id;
   private Integer estado;

   // Mapeamento com herança
   @JsonIgnore // O pedido de um pagamento não será serializado
   @OneToOne
   @JoinColumn(name = "pedido_id")
   @MapsId
   private Pedido pedido;

   public Pagamento() {}

   public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido)
   {
      this.id = id;
      this.estado = estado.getCodigo();
      this.pedido = pedido;
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public EstadoPagamento getEstado()
   {
      return EstadoPagamento.converteParaEnum(estado);
   }

   public void setEstado(EstadoPagamento estado)
   {
      this.estado = estado.getCodigo();
   }

   public Pedido getPedido()
   {
      return pedido;
   }

   public void setPedido(Pedido pedido)
   {
      this.pedido = pedido;
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null || getClass() != o.getClass()) return false;
      Pagamento pagamento = (Pagamento) o;
      return Objects.equals(id, pagamento.id);
   }

   @Override
   public int hashCode()
   {
      return Objects.hashCode(id);
   }
}
