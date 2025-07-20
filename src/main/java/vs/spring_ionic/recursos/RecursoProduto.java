package vs.spring_ionic.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vs.spring_ionic.dto.DtoProduto;
import vs.spring_ionic.entidades.Produto;
import vs.spring_ionic.servicos.ServicoProduto;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class RecursoProduto
{
   @Autowired
   private ServicoProduto servico;

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Produto> buscar(@PathVariable Integer id)
   {
      Produto obj = servico.buscar(id);
      return ResponseEntity.ok().body(obj);
   }

   // Buscar produtos por página
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<Page<DtoProduto>> buscarPagina
   (@RequestParam(value = "nome", defaultValue = "") String nome,
    @RequestParam(value = "categorias", defaultValue = "") String categorias,
    @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
    @RequestParam(value = "linhasPorPagina", defaultValue = "24")Integer linhasPorPagina,
    @RequestParam(value = "direcao", defaultValue = "ASC")String direcao,
    @RequestParam(value = "ordenadoPor", defaultValue = "nome")String ordenadoPor)
   {
      String nomeDecodificado = URL.converteParametro(nome);
      List<Integer> ids = URL.converteListaInteiros(categorias);
      Page<Produto> lista = servico.buscaPaginada(nomeDecodificado, ids, pagina, linhasPorPagina, direcao, ordenadoPor);
      Page<DtoProduto> listaDto = lista.map(DtoProduto::new);
      return ResponseEntity.ok().body(listaDto);
   }
}
