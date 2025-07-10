package vs.spring_ionic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vs.spring_ionic.entidades.Categoria;
import vs.spring_ionic.entidades.Produto;
import vs.spring_ionic.repositorios.RepositorioCategoria;
import vs.spring_ionic.repositorios.RepositorioProduto;

import java.util.Arrays;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner
{
	@Autowired
	private RepositorioCategoria repositorioCategoria;
	@Autowired
	private RepositorioProduto repositorioProduto;

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
	}
}
