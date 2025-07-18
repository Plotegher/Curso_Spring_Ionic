package vs.spring_ionic.servicos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vs.spring_ionic.dto.DtoClienteNovo;
import vs.spring_ionic.entidades.TipoCliente;
import vs.spring_ionic.excecoes.CampoMensagem;
import vs.spring_ionic.utilidades.ValidaCpfCnpj;

import java.util.ArrayList;
import java.util.List;

public class ValidadorIncluirCliente implements ConstraintValidator<IncluirCliente, DtoClienteNovo>
{
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
