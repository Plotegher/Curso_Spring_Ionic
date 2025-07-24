package vs.spring_ionic.utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import vs.spring_ionic.servicos.ServicoBD;
import vs.spring_ionic.servicos.ServicoEmail;
import vs.spring_ionic.servicos.ServicoEmailSmtp;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class ConfiguracaoDev
{
   @Autowired
   private ServicoBD servicoBD;

   @Value("${spring.jpa.hibernate.ddl-auto}")
   private String estrategia;

   @Bean
   public boolean instanciarBD() throws ParseException
   {
      if (!"create".equals(estrategia))
      {
         return false;
      }
      servicoBD.testeInstanciacaoBD();
      return true;
   }

   @Bean
   public ServicoEmail servicoEmail()
   {
      return new ServicoEmailSmtp();
   }
}
