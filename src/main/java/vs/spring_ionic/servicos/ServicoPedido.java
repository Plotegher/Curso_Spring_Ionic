package vs.spring_ionic.servicos;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.EstadoPagamento;
import vs.spring_ionic.entidades.ItemPedido;
import vs.spring_ionic.entidades.PagamentoBoleto;
import vs.spring_ionic.entidades.Pedido;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioItemPedido;
import vs.spring_ionic.repositorios.RepositorioPagamento;
import vs.spring_ionic.repositorios.RepositorioPedido;

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
}
