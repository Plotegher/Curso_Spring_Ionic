package vs.spring_ionic.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.entidades.Pedido;

import javax.transaction.Transactional;

@Repository
public interface RepositorioPedido extends JpaRepository<Pedido, Integer>
{
   @Transactional
   Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
