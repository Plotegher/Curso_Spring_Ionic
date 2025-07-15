package vs.spring_ionic.excecoes;

import java.io.Serializable;

public class ErroPadrao implements Serializable
{
   private Integer status;
   private String mensagem;
   private Long instante;

   public ErroPadrao(Integer status, String mensagem, Long instante)
   {
      this.status = status;
      this.mensagem = mensagem;
      this.instante = instante;
   }

   public Integer getStatus()
   {
      return status;
   }

   public void setStatus(Integer status)
   {
      this.status = status;
   }

   public String getMensagem()
   {
      return mensagem;
   }

   public void setMensagem(String mensagem)
   {
      this.mensagem = mensagem;
   }

   public Long getInstante()
   {
      return instante;
   }

   public void setInstante(Long instante)
   {
      this.instante = instante;
   }
}
