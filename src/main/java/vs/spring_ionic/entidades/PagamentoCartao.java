package vs.spring_ionic.entidades;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;

@Entity
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento
{
   private Integer numeroParcelas;

   public PagamentoCartao() {}

   public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas)
   {
      super(id, estado, pedido);
      this.numeroParcelas = numeroParcelas;
   }

   public Integer getNumeroParcelas()
   {
      return numeroParcelas;
   }

   public void setNumeroParcelas(Integer numeroParcelas)
   {
      this.numeroParcelas = numeroParcelas;
   }
}
