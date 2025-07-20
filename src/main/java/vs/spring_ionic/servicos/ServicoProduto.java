package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.Categoria;
import vs.spring_ionic.entidades.Produto;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioCategoria;
import vs.spring_ionic.repositorios.RepositorioProduto;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoProduto
{
   @Autowired
   private RepositorioProduto repositorioProduto;
   @Autowired
   private RepositorioCategoria repositorioCategoria;

   public Produto buscar(Integer id)
   {
      Optional<Produto> obj = repositorioProduto.findById(id);
      return obj.orElseThrow(() -> new ExcecaoObjectNotFound
            ("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
   }

   public Page<Produto> buscaPaginada(String nome, List<Integer> ids, Integer pagina,
                              Integer linhasPorPagina, String direcao, String ordenadoPor)
   {
      PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), ordenadoPor);
      List<Categoria> categorias = repositorioCategoria.findAllById(ids);
      return repositorioProduto.buscar(nome, categorias, pageRequest);
   }
}
