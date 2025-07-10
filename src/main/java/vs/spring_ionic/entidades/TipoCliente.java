package vs.spring_ionic.entidades;

public enum TipoCliente
{
   FISICA(1, "Pessoa Física"),
   JURIDICA(2, "Pessoa Jurídica");

   private Integer codigo;
   private String descricao;

   private TipoCliente(Integer codigo, String descricao)
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

   public static TipoCliente converteParaEnum(Integer codigo)
   {
      if (codigo == null)
      {
         return null;
      }

      for (TipoCliente c : TipoCliente.values())
      {
         if (codigo.equals(c.getCodigo()))
         {
            return c;
         }
      }

      throw new IllegalArgumentException("Id inválido: " + codigo);
   }
}
