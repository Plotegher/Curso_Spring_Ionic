package vs.spring_ionic.excecoes;

public class ExcecaoAuthorization extends RuntimeException
{
   public ExcecaoAuthorization(String mensagem)
   {
      super(mensagem);
   }

   public ExcecaoAuthorization(String mensagem, Throwable causa)
   {
     super(mensagem, causa);
   }
}
