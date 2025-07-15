package vs.spring_ionic.excecoes;

import java.io.Serializable;

public class CampoMensagem implements Serializable
{
   private String campo;
   private String mensagem;

   public CampoMensagem() {}

   public CampoMensagem(String campo, String mensagem)
   {
      this.campo = campo;
      this.mensagem = mensagem;
   }

   public String getNome()
   {
      return campo;
   }

   public void setNome(String campo)
   {
      this.campo = campo;
   }

   public String getMensagem()
   {
      return mensagem;
   }

   public void setMensagem(String mensagem)
   {
      this.mensagem = mensagem;
   }
}
