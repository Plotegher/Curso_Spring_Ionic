package vs.spring_ionic.excecoes;

public class ExcecaoObjectNotFound extends RuntimeException
{
   public ExcecaoObjectNotFound(String mensagem)
   {
      super(mensagem);
   }

   public ExcecaoObjectNotFound(String mensagem, Throwable causa)
   {
      super(mensagem, causa);
   }
}
