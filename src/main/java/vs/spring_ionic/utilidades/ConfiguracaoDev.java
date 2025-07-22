package vs.spring_ionic.utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import vs.spring_ionic.servicos.ServicoBD;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class ConfiguracaoDev
{
   @Autowired
   private ServicoBD servicoBD;

   @Value("S{spring.jpa.hibernate.ddl-auto}")
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
}
