package vs.spring_ionic.servicos;

import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.PagamentoBoleto;

import java.util.Calendar;
import java.util.Date;

@Service
public class ServicoBoleto
{
   public void preencherPagamentoBoleto(PagamentoBoleto pagamento, Date instantePedido)
   {
      Calendar calendario = Calendar.getInstance();
      calendario.setTime(instantePedido);
      calendario.add(Calendar.DAY_OF_MONTH, 30);
      pagamento.setDataVencimento(calendario.getTime());
   }
}
