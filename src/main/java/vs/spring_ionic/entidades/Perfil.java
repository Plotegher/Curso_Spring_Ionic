package vs.spring_ionic.entidades;

public enum Perfil
{
   ADMIN(1, "ROLE_ADMIN"),
   CLIENTE(2, "ROLE_CLIENTE");

   private Integer codigo;
   private String descricao;

   private Perfil(Integer codigo, String descricao)
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

   public static Perfil converteParaEnum(Integer codigo)
   {
      if (codigo == null)
      {
         return null;
      }

      for (Perfil c : Perfil.values())
      {
         if (codigo.equals(c.getCodigo()))
         {
            return c;
         }
      }

      throw new IllegalArgumentException("Id inválido: " + codigo);
   }
}
