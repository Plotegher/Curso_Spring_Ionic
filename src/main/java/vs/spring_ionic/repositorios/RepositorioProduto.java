package vs.spring_ionic.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import vs.spring_ionic.entidades.Produto;

public interface RepositorioProduto extends JpaRepository<Produto, Integer> {}
