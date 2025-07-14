package vs.spring_ionic.excecoes;

public class ExcecaoDataIntegrity extends RuntimeException
{
   public ExcecaoDataIntegrity(String mensagem)
   {
      super(mensagem);
   }

   public ExcecaoDataIntegrity(String mensagem, Throwable causa)
   {
     super(mensagem, causa);
   }
}
