package vs.spring_ionic.repositorios;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vs.spring_ionic.entidades.Categoria;
import vs.spring_ionic.entidades.Produto;

import java.util.List;

@Repository
public interface RepositorioProduto extends JpaRepository<Produto, Integer>
{
   // Query alterada para contemplar a pesquisa case insensitive
   @Transactional
   @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE UPPER(obj.nome)" +
         " LIKE UPPER(concat('%',:nome,'%')) AND cat IN :categorias")
   Page<Produto> buscar(@Param("nome") String nome,
                        @Param("categorias") List<Categoria> categorias,
                        Pageable pageRequest);
}

/*
// Consulta original:
   "SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE" +
       " obj.nome LIKE %:nome% AND cat IN :categorias"

   Ou sem @Query com o nome do método alterado para:
   Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,
       List<Categoria> categorias, Pageable pageRequest);
*/
