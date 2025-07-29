package vs.spring_ionic.servicos;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.*;
import vs.spring_ionic.excecoes.ExcecaoAuthorization;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioItemPedido;
import vs.spring_ionic.repositorios.RepositorioPagamento;
import vs.spring_ionic.repositorios.RepositorioPedido;
import vs.spring_ionic.utilidades.UsuarioSpringSecurity;

import java.util.Date;
import java.util.Optional;

@Service
public class ServicoPedido
{
   @Autowired
   private RepositorioPedido repositorioPedido;

   @Autowired
   private ServicoBoleto servicoBoleto;

   @Autowired
   private RepositorioPagamento repositorioPagamento;

   @Autowired
   private ServicoProduto servicoProduto;

   @Autowired
   private RepositorioItemPedido repositorioItemPedido;

   @Autowired
   private ServicoCliente servicoCliente;

   @Autowired
   private ServicoEmail servicoEmail;

   public Pedido buscar(Integer id)
   {
      Optional<Pedido> obj = repositorioPedido.findById(id);
      return obj.orElseThrow(() -> new ExcecaoObjectNotFound
            ("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
   }

   @Transactional
   public Pedido incluir(Pedido obj)
   {
      obj.setId(null);
      obj.setInstante(new Date());
      obj.setCliente(servicoCliente.buscar(obj.getCliente().getId()));
      obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
      obj.getPagamento().setPedido(obj);
      if (obj.getPagamento() instanceof PagamentoBoleto)
      {
         PagamentoBoleto pagamento = (PagamentoBoleto) obj.getPagamento();
         servicoBoleto.preencherPagamentoBoleto(pagamento, obj.getInstante());
      }
      obj = repositorioPedido.save(obj);
      repositorioPagamento.save(obj.getPagamento());
      for (ItemPedido item : obj.getItens())
      {
         item.setDesconto(0.0);
         item.setProduto(servicoProduto.buscar(item.getProduto().getId()));
         item.setPreco(item.getProduto().getPreco());
         item.setPedido(obj);
      }
      repositorioItemPedido.saveAll(obj.getItens());
      servicoEmail.enviarConfirmacaoPedidoHtml(obj);
      return obj;
   }

   public Page<Pedido> buscarPagina(Integer pagina, Integer linhasPorPagina,
         String direcao, String ordenadoPor)
   {
      UsuarioSpringSecurity usuarioSS = ServicoUsuario.autenticado();
      if (usuarioSS == null)
      {
         throw new ExcecaoAuthorization("Acesso negado!");
      }
      PageRequest pageRequest = PageRequest.of(pagina,
            linhasPorPagina,
            Sort.Direction.valueOf(direcao),
            ordenadoPor);
      Cliente cliente = servicoCliente.buscar(usuarioSS.getId());
      return repositorioPedido.findByCliente(cliente, pageRequest);
   }
}
