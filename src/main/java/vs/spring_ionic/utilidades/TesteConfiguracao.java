package vs.spring_ionic.utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import vs.spring_ionic.servicos.ServicoBD;

import java.text.ParseException;

@Configuration
@Profile("teste")
public class TesteConfiguracao
{
   @Autowired
   private ServicoBD servicoBD;

   @Bean
   public boolean instanciarBD() throws ParseException
   {
      servicoBD.testeInstanciacaoBD();
      return true;
   }
}
