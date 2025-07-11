package vs.spring_ionic.entidades;

public enum EstadoPagamento
{
   PENDENTE(1, "Pendente"),
   QUITADO(2, "Quitado"),
   CANCELADO(3, "Cancelado");

   private Integer codigo;
   private String descricao;

   private EstadoPagamento(Integer codigo, String descricao)
   {
      this.codigo = codigo;
      this.descricao = descricao;
   }

   public Integer getCodigo()
   {
      return codigo;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public static EstadoPagamento converteParaEnum(Integer codigo)
   {
      if (codigo == null)
      {
         return null;
      }

      for (EstadoPagamento c : EstadoPagamento.values())
      {
         if (codigo.equals(c.getCodigo()))
         {
            return c;
         }
      }

      throw new IllegalArgumentException("Id inválido: " + codigo);
   }
}
