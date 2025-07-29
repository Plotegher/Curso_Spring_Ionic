package vs.spring_ionic.servicos;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.entidades.Pedido;

import java.util.Date;

public abstract class ServicoEmailAbstrato implements ServicoEmail
{
   @Value("${default.sender}")
   private String sender;

   @Autowired
   private TemplateEngine templateEngine;

   @Autowired
   private JavaMailSender javaMailSender;

   @Override
   public void enviarConfirmacaoPedidoTexto(Pedido obj)
   {
      SimpleMailMessage simpleMailMessage = preparaMensagemPedidoTexto(obj);
      enviarEmailTexto(simpleMailMessage);
   }

   public void enviarConfirmacaoPedidoHtml(Pedido obj)
   {
      try
      {
         MimeMessage mimeMessage = preparaMensagemPedidoHtml(obj);
         enviarEmailHtml(mimeMessage);
      }
      catch (MessagingException e)
      {
         enviarConfirmacaoPedidoTexto(obj);
      }
   }

   protected SimpleMailMessage preparaMensagemPedidoTexto(Pedido obj)
   {
      SimpleMailMessage smm = new SimpleMailMessage();
      smm.setTo(obj.getCliente().getEmail());
      smm.setFrom(sender);
      smm.setSubject("Pedido " + obj.getId() + " confirmado!");
      smm.setSentDate(new Date(System.currentTimeMillis()));
      smm.setText(obj.toString());
      return smm;
   }

   protected MimeMessage preparaMensagemPedidoHtml(Pedido obj) throws MessagingException
   {
      MimeMessage mmm = javaMailSender.createMimeMessage();
      MimeMessageHelper mmh = new MimeMessageHelper(mmm ,true);
      mmh.setTo(obj.getCliente().getEmail());
      mmh.setFrom(sender);
      mmh.setSubject("Pedido " + obj.getId() + " confirmado!");
      mmh.setSentDate(new Date(System.currentTimeMillis()));
      mmh.setText(htmlPedidoModelo(obj), true);
      return mmh.getMimeMessage(); // adicionei .getMimeMessage()
   }

   protected String htmlPedidoModelo(Pedido obj)
   {
      Context contexto = new Context();
      contexto.setVariable("pedido", obj);
      return templateEngine.process("email/confirmacaoPedido", contexto);
   }

   @Override
   public void enviarEmailNovaSenha(Cliente cliente, String novaSenha)
   {
      SimpleMailMessage smm = preparaEmailNovaSenha(cliente, novaSenha);
      enviarEmailTexto(smm);
   }

   protected SimpleMailMessage preparaEmailNovaSenha(Cliente cliente, String novaSenha)
   {
      SimpleMailMessage smm = new SimpleMailMessage();
      smm.setTo(cliente.getEmail());
      smm.setFrom(sender);
      smm.setSubject("Solicitação de nova senha!");
      smm.setSentDate(new Date(System.currentTimeMillis()));
      smm.setText("Nova senha: " + novaSenha);
      return smm;
   }
}
