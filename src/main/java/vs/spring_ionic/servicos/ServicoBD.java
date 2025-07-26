package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.*;
import vs.spring_ionic.repositorios.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class ServicoBD
{
   @Autowired
   private RepositorioCategoria repositorioCategoria;
   @Autowired
   private RepositorioProduto repositorioProduto;
   @Autowired
   private RepositorioEstado repositorioEstado;
   @Autowired
   private RepositorioMunicipio repositorioMunicipio;
   @Autowired
   private RepositorioCliente repositorioCliente;
   @Autowired
   private RepositorioEndereco repositorioEndereco;
   @Autowired
   private RepositorioPedido repositorioPedido;
   @Autowired
   private RepositorioPagamento repositorioPagamento;
   @Autowired
   private RepositorioItemPedido repositorioItemPedido;
   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   public void testeInstanciacaoBD() throws ParseException
   {
      Categoria cat1 = new Categoria(null, "Informática");
      Categoria cat2 = new Categoria(null, "Escritório");
      Categoria cat3 = new Categoria(null, "Cama mesa e banho");
      Categoria cat4 = new Categoria(null, "Eletrônicos");
      Categoria cat5 = new Categoria(null, "Jardinagem");
      Categoria cat6 = new Categoria(null, "Decoração");
      Categoria cat7 = new Categoria(null, "Perfumaria");

      Produto pro1 = new Produto(null, "Computador", 2000.00);
      Produto pro2 = new Produto(null, "Impressora", 800.00);
      Produto pro3 = new Produto(null, "Mouse", 80.00);
      Produto pro4 = new Produto(null, "Mesa de escritório", 300.00);
      Produto pro5 = new Produto(null, "Toalha", 50.00);
      Produto pro6 = new Produto(null, "Colcha", 200.00);
      Produto pro7 = new Produto(null, "Tv colorida", 1200.00);
      Produto pro8 = new Produto(null, "Roçadeira", 800.00);
      Produto pro9 = new Produto(null, "Abajur", 100.00);
      Produto pro10 = new Produto(null, "Pendente", 180.00);
      Produto pro11 = new Produto(null, "Shampoo", 90.00);

      cat1.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3));
      cat2.getProdutos().addAll(Arrays.asList(pro2, pro4));
      cat3.getProdutos().addAll(Arrays.asList(pro5, pro6));
      cat4.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3, pro7));
      cat5.getProdutos().addAll(Arrays.asList(pro8));
      cat6.getProdutos().addAll(Arrays.asList(pro9, pro10));
      cat7.getProdutos().addAll(Arrays.asList(pro11));

      pro1.getCategorias().addAll(Arrays.asList(cat1, cat4));
      pro2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
      pro3.getCategorias().addAll(Arrays.asList(cat1, cat4));
      pro4.getCategorias().addAll(Arrays.asList(cat2));
      pro5.getCategorias().addAll(Arrays.asList(cat3));
      pro6.getCategorias().addAll(Arrays.asList(cat3));
      pro7.getCategorias().addAll(Arrays.asList(cat4));
      pro8.getCategorias().addAll(Arrays.asList(cat5));
      pro9.getCategorias().addAll(Arrays.asList(cat6));
      pro10.getCategorias().addAll(Arrays.asList(cat6));
      pro11.getCategorias().addAll(Arrays.asList(cat7));

      repositorioCategoria.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
      repositorioProduto.saveAll(Arrays.asList(pro1, pro2, pro3, pro4, pro5, pro6, pro7, pro8, pro9, pro10, pro11));

      Estado est1 = new Estado(null, "Minas Gerais");
      Estado est2 = new Estado(null, "Espírito Santo");

      Municipio mun1 = new Municipio(null, "Uberlândia", est1);
      Municipio mun2 = new Municipio(null, "Vitória", est2);
      Municipio mun3 = new Municipio(null, "Vila Velha", est2);

      est1.getMunicipios().addAll(Arrays.asList(mun1));
      est2.getMunicipios().addAll(Arrays.asList(mun2, mun3));

      repositorioEstado.saveAll(Arrays.asList(est1, est2));
      repositorioMunicipio.saveAll(Arrays.asList(mun1, mun2, mun3));

      Cliente cli1 = new Cliente(null, "Maria Silva", "herhos@gmail.com",
            "123.456.789-10", TipoCliente.FISICA, bCryptPasswordEncoder.encode("123"));

      cli1.getTelefones().addAll(Arrays.asList("98765-4321", "99887-7665"));

      Endereco end1 = new Endereco(null, "Rua Flores", "300", "AP 203",
            "Floresta", "38.204-054", cli1, mun1);
      Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 801",
            "Centro", "29.050-002", cli1, mun2);

      cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

      repositorioCliente.saveAll(Arrays.asList(cli1));
      repositorioEndereco.saveAll(Arrays.asList(end1, end2));

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

      Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
      Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);

      Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
      ped1.setPagamento(pag1);
      Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE,
            ped2, sdf.parse("20/10/2017 00:00"), null);
      ped2.setPagamento(pag2);

      cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

      repositorioPedido.saveAll(Arrays.asList(ped1, ped2));
      repositorioPagamento.saveAll(Arrays.asList(pag1, pag2));

      ItemPedido itp1 = new ItemPedido(ped1, pro1, 0.0, 1, 2000.0);
      ItemPedido itp2 = new ItemPedido(ped1, pro3, 0.0, 2, 80.0);
      ItemPedido itp3 = new ItemPedido(ped2, pro2, 100.0, 1, 800.0);

      ped1.getItens().addAll(Arrays.asList(itp1, itp2));
      ped2.getItens().addAll(Arrays.asList(itp3));

      pro1.getItens().addAll(Arrays.asList(itp1));
      pro2.getItens().addAll(Arrays.asList(itp3));
      pro3.getItens().addAll(Arrays.asList(itp2));

      repositorioItemPedido.saveAll(Arrays.asList(itp1, itp2, itp3));
   }
}
