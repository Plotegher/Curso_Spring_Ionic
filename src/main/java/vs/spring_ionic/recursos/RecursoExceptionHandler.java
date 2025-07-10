package vs.spring_ionic.recursos;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;

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
}
