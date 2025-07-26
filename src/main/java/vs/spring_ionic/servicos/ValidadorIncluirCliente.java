package vs.spring_ionic.servicos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import vs.spring_ionic.dtos.DtoClienteNovo;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.entidades.TipoCliente;
import vs.spring_ionic.excecoes.CampoMensagem;
import vs.spring_ionic.repositorios.RepositorioCliente;
import vs.spring_ionic.utilidades.ValidaCpfCnpj;

import java.util.ArrayList;
import java.util.List;

public class ValidadorIncluirCliente implements ConstraintValidator<IncluirCliente, DtoClienteNovo>
{
   @Autowired
   private RepositorioCliente repositorio;

   @Override
   public void initialize(IncluirCliente incluirCliente) {}

   @Override
   public boolean isValid(DtoClienteNovo objDto, ConstraintValidatorContext context) {

      List<CampoMensagem> lista = new ArrayList<>();

      if (objDto.getTipoCliente().equals(TipoCliente.FISICA.getCodigo())
            && !ValidaCpfCnpj.isValidCpf(objDto.getCpfCnpj()))
      {
         lista.add(new CampoMensagem("cpfCnpj", "CPF inválido!"));
      }

      if (objDto.getTipoCliente().equals(TipoCliente.JURIDICA.getCodigo())
            && !ValidaCpfCnpj.isValidCnpj(objDto.getCpfCnpj()))
      {
         lista.add(new CampoMensagem("cpfCnpj", "CNPJ inválido!"));
      }

      Cliente auxiliar = repositorio.findByEmail(objDto.getEmail());
      if (auxiliar != null)
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
