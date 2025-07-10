package vs.spring_ionic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vs.spring_ionic.entidades.Categoria;
import vs.spring_ionic.repositorios.RepositorioCategoria;

import java.util.Arrays;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner
{
	@Autowired
	private RepositorioCategoria repositorioCategoria;

	public static void main(String[] args)
	{
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		repositorioCategoria.saveAll(Arrays.asList(cat1, cat2));
	}
}
