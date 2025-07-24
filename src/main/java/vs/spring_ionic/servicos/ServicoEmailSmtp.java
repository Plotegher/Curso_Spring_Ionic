package vs.spring_ionic.servicos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class ServicoEmailSmtp extends ServicoEmailAbstrato
{
   @Autowired
   private MailSender mailSender;

   private static final Logger LOGGER = LoggerFactory.getLogger(ServicoEmailSmtp.class);

   @Override
   public void enviarEmail(SimpleMailMessage mensagem)
   {
      LOGGER.info("Enviando e-mail ...");
      mailSender.send(mensagem);
      LOGGER.info("E-mail enviado!");
   }
}
