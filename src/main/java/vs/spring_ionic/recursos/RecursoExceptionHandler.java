package vs.spring_ionic.recursos;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vs.spring_ionic.excecoes.*;

@ControllerAdvice
public class RecursoExceptionHandler
{
   @ExceptionHandler(ExcecaoObjectNotFound.class)
   public ResponseEntity<ErroPadrao> objectNotFound(ExcecaoObjectNotFound excecao, HttpServletRequest request)
   {
      ErroPadrao erro = new ErroPadrao(HttpStatus.NOT_FOUND.value(), excecao.getMessage(),
            System.currentTimeMillis());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
   }

   @ExceptionHandler(ExcecaoDataIntegrity.class)
   public ResponseEntity<ErroPadrao> dataIntegrity(ExcecaoDataIntegrity excecao, HttpServletRequest request)
   {
      ErroPadrao erro = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), excecao.getMessage(),
            System.currentTimeMillis());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ErroPadrao> validacao(MethodArgumentNotValidException excecao,
                                                            HttpServletRequest request)
   {
      ErroValidacao erro = new ErroValidacao(HttpStatus.BAD_REQUEST.value(), "Erro de validação!",
            System.currentTimeMillis());
      for (FieldError e : excecao.getBindingResult().getFieldErrors())
      {
         erro.adicionaErro(e.getField(), e.getDefaultMessage());
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
   }

   @ExceptionHandler(ExcecaoAuthorization.class)
   public ResponseEntity<ErroPadrao> authorization(ExcecaoAuthorization excecao, HttpServletRequest request)
   {
      ErroPadrao erro = new ErroPadrao(HttpStatus.FORBIDDEN.value(), excecao.getMessage(),
            System.currentTimeMillis());
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
   }
}
