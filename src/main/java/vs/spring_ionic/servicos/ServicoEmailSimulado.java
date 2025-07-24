package vs.spring_ionic.servicos;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class ServicoEmailSimulado extends ServicoEmailAbstrato
{
   private static final Logger LOGGER = LoggerFactory.getLogger(ServicoEmailSimulado.class);

   @Override
   public void enviarEmailTexto(SimpleMailMessage mensagem)
   {
      LOGGER.info("Simulando o envio de e-mail texto ...");
      LOGGER.info(mensagem.toString());
      LOGGER.info("E-mail enviado!");
   }

   @Override
   public void enviarEmailHtml(MimeMessage mensagem)
   {
      LOGGER.info("Simulando o envio de e-mail html ...");
      LOGGER.info(mensagem.toString());
      LOGGER.info("E-mail enviado!");
   }
}
