package vs.spring_ionic.recursos;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vs.spring_ionic.dtos.DtoCliente;
import vs.spring_ionic.dtos.DtoClienteNovo;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.servicos.ServicoCliente;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class RecursoCliente
{
   @Autowired
   private ServicoCliente servico;

   // Buscar um cliente
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Cliente> buscar(@PathVariable Integer id)
   {
      Cliente obj = servico.buscar(id);
      return ResponseEntity.ok().body(obj);
   }

   // Buscar todos os clientes
   @PreAuthorize("hasAnyRole('ADMIN')")
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<DtoCliente>> buscarTudo()
   {
      List<Cliente> lista = servico.buscarTudo();
      List<DtoCliente> listaDto = lista.stream().map(DtoCliente::new).toList();
      return ResponseEntity.ok().body(listaDto);
   }

   // Buscar clientes por página
   @PreAuthorize("hasAnyRole('ADMIN')")
   @RequestMapping(value = "/pagina", method = RequestMethod.GET)
   public ResponseEntity<Page<DtoCliente>> buscarPagina
   (@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
    @RequestParam(value = "linhasPorPagina", defaultValue = "24")Integer linhasPorPagina,
    @RequestParam(value = "direcao", defaultValue = "ASC")String direcao,
    @RequestParam(value = "ordenadoPor", defaultValue = "nome")String ordenadoPor)
   {
      Page<Cliente> lista = servico.buscarPagina(pagina, linhasPorPagina, direcao, ordenadoPor);
      Page<DtoCliente> listaDto = lista.map(DtoCliente::new);
      return ResponseEntity.ok().body(listaDto);
   }

   // Incluir um cliente
   @RequestMapping(method=RequestMethod.POST)
   public ResponseEntity<Void> incluir(@Valid @RequestBody DtoClienteNovo objDto) {
      Cliente obj = servico.origemDto(objDto);
      obj = servico.incluir(obj);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(obj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   // Atualizar um cliente
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> atualizar(@Valid @RequestBody DtoCliente objDto, @PathVariable Integer id)
   {
      Cliente obj = servico.origemDto(objDto);
      obj.setId(id);
      obj = servico.atualizar(obj);
      return ResponseEntity.noContent().build();
   }

   // Excluir um cliente
   @PreAuthorize("hasAnyRole('ADMIN')")
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> excluir(@PathVariable Integer id)
   {
      servico.excluir(id);
      return ResponseEntity.noContent().build();
   }
}
