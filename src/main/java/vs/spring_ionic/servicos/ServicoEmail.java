package vs.spring_ionic.servicos;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import vs.spring_ionic.entidades.Pedido;

public interface ServicoEmail
{
   // Métodos para enviar e-mails no modo texto
   void enviarConfirmacaoPedidoTexto(Pedido pedido);
   void enviarEmailTexto(SimpleMailMessage mensagem);

   // Métodos para enviar e-mails no modo html
   void enviarConfirmacaoPedidoHtml(Pedido obj);
   void enviarEmailHtml(MimeMessage msg);
}
