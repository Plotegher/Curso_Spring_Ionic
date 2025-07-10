package vs.spring_ionic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vs.spring_ionic.entidades.*;
import vs.spring_ionic.repositorios.*;

import java.util.Arrays;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner
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

	public static void main(String[] args)
	{
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto prod1 = new Produto(null, "Computador", 2000.0);
		Produto prod2 = new Produto(null, "Impressora", 800.0);
		Produto prod3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));

		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));

		repositorioCategoria.saveAll(Arrays.asList(cat1, cat2));
		repositorioProduto.saveAll(Arrays.asList(prod1, prod2, prod3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Espírito Santo");

		Municipio mun1 = new Municipio(null, "Uberlândia", est1);
		Municipio mun2 = new Municipio(null, "Vitória", est2);
		Municipio mun3 = new Municipio(null, "Vila Velha", est2);

		est1.getMunicipios().addAll(Arrays.asList(mun1));
		est2.getMunicipios().addAll(Arrays.asList(mun2, mun3));

		repositorioEstado.saveAll(Arrays.asList(est1, est2));
		repositorioMunicipio.saveAll(Arrays.asList(mun1, mun2, mun3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@email.com",
				"123.456.789-10", TipoCliente.FISICA);

		cli1.getTelefones().addAll(Arrays.asList("98765-4321", "99887-7665"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "AP 203",
				"Floresta", "38.204-054", cli1, mun1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 801",
				"Centro", "29.050-002", cli1, mun2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		repositorioCliente.saveAll(Arrays.asList(cli1));
		repositorioEndereco.saveAll(Arrays.asList(end1, end2));
	}
}
