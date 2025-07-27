package vs.spring_ionic.repositorios;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vs.spring_ionic.entidades.Cliente;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Integer>
{
   @Transactional
   Cliente findByEmail(String email);
}
