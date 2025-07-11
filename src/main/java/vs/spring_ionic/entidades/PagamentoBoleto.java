package vs.spring_ionic.entidades;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class PagamentoBoleto extends Pagamento
{
   private Date dataVencimento;
   private Date dataPagamento;

   public PagamentoBoleto() {}

   public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido,
                          Date dataVencimento, Date dataPagamento)
   {
      super(id, estado, pedido);
      this.dataVencimento = dataVencimento;
      this.dataPagamento = dataPagamento;
   }

   public Date getDataVencimento()
   {
      return dataVencimento;
   }

   public void setDataVencimento(Date dataVencimento)
   {
      this.dataVencimento = dataVencimento;
   }

   public Date getDataPagamento()
   {
      return dataPagamento;
   }

   public void setDataPagamento(Date dataPagamento)
   {
      this.dataPagamento = dataPagamento;
   }
}
