package vs.spring_ionic.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vs.spring_ionic.entidades.Pagamento;

@Repository
public interface RepositorioPagamento extends JpaRepository<Pagamento, Integer> {}
