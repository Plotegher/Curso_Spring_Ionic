package vs.spring_ionic.servicos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class ServicoEmailSimulado extends ServicoEmailAbstrato
{
   private static final Logger LOGGER = LoggerFactory.getLogger(ServicoEmailSimulado.class);

   @Override
   public void enviarEmail(SimpleMailMessage mensagem)
   {
      LOGGER.info("Simulando o envio de e-mail ...");
      LOGGER.info(mensagem.toString());
      LOGGER.info("E-mail enviado!");
   }
}
