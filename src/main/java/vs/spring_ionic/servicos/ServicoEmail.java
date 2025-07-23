package vs.spring_ionic.servicos;

import org.springframework.mail.SimpleMailMessage;
import vs.spring_ionic.entidades.Pedido;

public interface ServicoEmail
{
   void enviarConfirmacaoPedido(Pedido pedido);
   void enviarEmail(SimpleMailMessage mensagem);
}
