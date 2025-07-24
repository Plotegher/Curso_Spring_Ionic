package vs.spring_ionic.servicos;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class ServicoEmailSmtp extends ServicoEmailAbstrato
{
   @Autowired
   private MailSender mailSender;

   @Autowired
   private JavaMailSender javaMailSender;

   private static final Logger LOGGER = LoggerFactory.getLogger(ServicoEmailSmtp.class);

   @Override
   public void enviarEmailTexto(SimpleMailMessage mensagem)
   {
      LOGGER.info("Enviando e-mail texto ...");
      mailSender.send(mensagem);
      LOGGER.info("E-mail enviado!");
   }

   @Override
   public void enviarEmailHtml(MimeMessage mensagem)
   {
      LOGGER.info("Enviando e-mail html ...");
      javaMailSender.send(mensagem);
      LOGGER.info("E-mail enviado!");
   }
}
