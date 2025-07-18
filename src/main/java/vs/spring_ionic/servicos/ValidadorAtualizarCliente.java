package vs.spring_ionic.servicos;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import vs.spring_ionic.dto.DtoCliente;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.excecoes.CampoMensagem;
import vs.spring_ionic.repositorios.RepositorioCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidadorAtualizarCliente implements ConstraintValidator<AtualizarCliente, DtoCliente>
{
   @Autowired
   private HttpServletRequest requisicao;

   @Autowired
   private RepositorioCliente repositorio;

   @Override
   public void initialize(AtualizarCliente atualizarCliente) {}

   @Override
   public boolean isValid(DtoCliente objDto, ConstraintValidatorContext context) {

      @SuppressWarnings("unchecked")
      Map<String, String> mapa = (Map<String, String>) requisicao
            .getAttribute(HandlerMapping
            .URI_TEMPLATE_VARIABLES_ATTRIBUTE);

      Integer uriId = Integer.parseInt(mapa.get("id"));

      List<CampoMensagem> lista = new ArrayList<>();

      Cliente auxiliar = repositorio.findByEmail(objDto.getEmail());
      if (auxiliar != null && !auxiliar.getId().equals(uriId))
      {
         lista.add(new CampoMensagem("email", "E-mail já existente!"));
      }

      for (CampoMensagem c : lista)
      {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(c.getMensagem())
               .addPropertyNode(c.getCampo())
               .addConstraintViolation();
      }
      return lista.isEmpty();
   }
}
