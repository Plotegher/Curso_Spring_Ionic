package vs.spring_ionic.repositorios;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vs.spring_ionic.entidades.Cliente;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Integer>
{
   @Transactional//(readOnly = true)
   Cliente findByEmail(String email);
}
