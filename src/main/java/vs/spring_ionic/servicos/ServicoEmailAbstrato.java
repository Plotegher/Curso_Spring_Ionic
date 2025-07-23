package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import vs.spring_ionic.entidades.Pedido;

import java.util.Date;

public abstract class ServicoEmailAbstrato implements ServicoEmail
{
   @Value("${default.sender}")
   private String sender;

   @Override
   public void enviarConfirmacaoPedido(Pedido obj)
   {
      SimpleMailMessage smm = preparaMensagemCorreioPedido(obj);
      enviarEmail(smm);
   }

   protected SimpleMailMessage preparaMensagemCorreioPedido(Pedido obj)
   {
      SimpleMailMessage smm = new SimpleMailMessage();
      smm.setTo(obj.getCliente().getEmail());
      smm.setFrom(sender);
      smm.setSubject("Pedido " + obj.getId() + " confirmado!");
      smm.setSentDate(new Date(System.currentTimeMillis()));
      smm.setText(obj.toString());
      return smm;
   }
}
