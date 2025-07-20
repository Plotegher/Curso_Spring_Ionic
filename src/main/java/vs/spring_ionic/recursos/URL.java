package vs.spring_ionic.recursos;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

public class URL
{
   public static String converteParametro(String s)
   {
      try
      {
         return URLDecoder.decode(s, "UTF-8");
      }
      catch (UnsupportedEncodingException e)
      {
         return "";
      }
   }

   public static List<Integer> converteListaInteiros(String s)
   {
      // Primeira forma:
//      String[] vetor = s.split(",");
//      List<Integer> lista = new ArrayList<>();
//      for (int i = 0; i < vetor.length; i++)
//      {
//         lista.add(Integer.parseInt(vetor[i]));
//      }
//      return lista;

      // Segunda forma:
      //return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).toList();

      // Terceira forma
      return Arrays.stream(s.split(",")).map(Integer::parseInt).toList();
   }
}
