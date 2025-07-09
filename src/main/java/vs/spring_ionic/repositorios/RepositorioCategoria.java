package vs.spring_ionic.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vs.spring_ionic.entidades.Categoria;

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Integer>
{

}
