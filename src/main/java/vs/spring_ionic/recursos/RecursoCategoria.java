package vs.spring_ionic.recursos;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vs.spring_ionic.dtos.DtoCategoria;
import vs.spring_ionic.entidades.Categoria;
import vs.spring_ionic.servicos.ServicoCategoria;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class RecursoCategoria
{
   @Autowired
   private ServicoCategoria servico;

   // Buscar uma categoria
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Categoria> buscar(@PathVariable Integer id)
   {
      Categoria obj = servico.buscar(id);
      return ResponseEntity.ok().body(obj);
   }

   // Buscar todas as categorias
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<DtoCategoria>> buscarTudo()
   {
      List<Categoria> lista = servico.buscarTudo();
      List<DtoCategoria> listaDto = lista.stream().map(DtoCategoria::new).toList();
      return ResponseEntity.ok().body(listaDto);
   }

   // Buscar categorias por página
   @RequestMapping(value = "/pagina", method = RequestMethod.GET)
   public ResponseEntity<Page<DtoCategoria>> buscarPagina
         (@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
          @RequestParam(value = "linhasPorPagina", defaultValue = "24")Integer linhasPorPagina,
          @RequestParam(value = "direcao", defaultValue = "ASC")String direcao,
          @RequestParam(value = "ordenadoPor", defaultValue = "nome")String ordenadoPor)
   {
      Page<Categoria> lista = servico.buscarPagina(pagina, linhasPorPagina, direcao, ordenadoPor);
      Page<DtoCategoria> listaDto = lista.map(DtoCategoria::new);
      return ResponseEntity.ok().body(listaDto);
   }

   // Incluir uma categoria
   @PreAuthorize("hasAnyRole('ADMIN')")
   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> incluir(@Valid @RequestBody DtoCategoria objDto)
   {
      Categoria obj = servico.origemDto(objDto);
      obj = servico.incluir(obj);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   // Atualizar uma categoria
   @PreAuthorize("hasAnyRole('ADMIN')")
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> atualizar(@Valid @RequestBody DtoCategoria objDto, @PathVariable Integer id)
   {
      Categoria obj = servico.origemDto(objDto);
      obj.setId(id);
      obj = servico.atualizar(obj);
      return ResponseEntity.noContent().build();
   }

   // Excluir uma categoria
   @PreAuthorize("hasAnyRole('ADMIN')")
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> excluir(@PathVariable Integer id)
   {
      servico.excluir(id);
      return ResponseEntity.noContent().build();
   }
}
