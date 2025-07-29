package vs.spring_ionic.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vs.spring_ionic.entidades.Cliente;
import vs.spring_ionic.excecoes.ExcecaoObjectNotFound;
import vs.spring_ionic.repositorios.RepositorioCliente;

import java.util.Random;

@Service
public class ServicoAuth
{
   @Autowired
   private RepositorioCliente repositorioCliente;

   @Autowired
   private BCryptPasswordEncoder bCrypt;

   @Autowired
   private ServicoEmail servicoEmail;

   private final Random random = new Random();

   public void envieNovaSenha(String email)
   {
      Cliente cliente = repositorioCliente.findByEmail(email);
      if (cliente == null)
      {
         throw new ExcecaoObjectNotFound("E-mail não encontrado!");
      }

      String senha = novaSenha();
      cliente.setSenha(bCrypt.encode(senha));
      repositorioCliente.save(cliente);
      servicoEmail.enviarEmailNovaSenha(cliente, senha);
   }

   private String novaSenha()
   {
      char[] vetor = new char[10];
      for (int i = 0; i < 10; i++)
      {
         vetor[i] = caracteresAleatorios();
      }
      return new String(vetor);
   }

   private char caracteresAleatorios()
   {
      int opcao = random.nextInt(3);

      if (opcao == 0) // Gera um dígito
      {
         return (char) (random.nextInt(10) + 48);
      }
      else if (opcao == 1) // Gera uma letra maiúscula
      {
         return (char) (random.nextInt(26) + 65);
      }
      else // Gera uma letra minúscula
      {
         return (char) (random.nextInt(26) + 97);
      }
   }
}
