package vs.spring_ionic.excecoes;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadrao
{
   private List<CampoMensagem> erros = new ArrayList<>();

   public ErroValidacao(Integer status, String mensagem, Long instante)
   {
      super(status, mensagem, instante);
   }

   public List<CampoMensagem> getErros()
   {
      return erros;
   }

   public void adicionaErro(String campo, String mensagem)
   {
      erros.add(new CampoMensagem(campo, mensagem));
   }
}
