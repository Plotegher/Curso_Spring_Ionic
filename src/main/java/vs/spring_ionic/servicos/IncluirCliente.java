package vs.spring_ionic.servicos;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidadorIncluirCliente.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IncluirCliente
{
   String message() default "Erro de validação!";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}
