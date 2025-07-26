package vs.spring_ionic.utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca //extends WebSecurityConfigurerAdapter
{
   @Autowired
   private Environment environment;

   // Caminhos liberados
   private static final String[] PUBLIC_MATCHERS =
         { "/h2-console/**" };

   // Caminhos liberados para leitura
   private static final String[] PUBLIC_MATCHERS_GET =
         { "/produtos/**", "/categorias/**" };

   // (1) Configurando HttpSecurity
   // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
   // (2) Spring Security 6: cors() está obsoleto e marcado para remoção
   // https://stackoverflow.com/questions/77266685/spring-security-6-cors-is-deprecated-and-marked-for-removal
   // (3) csrf.disable() obsoleto
   // Adriano César Ferreita - https://cursos.alura.com.br/forum/topico-metodos-deprecated-300205
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
   {
      if (Arrays.asList(environment.getActiveProfiles()).contains("test"))
      {
         http.headers(((headers) ->
            headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)));
      }

      // (3)
      http.csrf(AbstractHttpConfigurer::disable);
      http.authorizeHttpRequests((authz) ->
         authz.anyRequest().authenticated()).httpBasic(withDefaults());
      http.csrf(AbstractHttpConfigurer::disable).sessionManagement(session ->
         session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      return http.build();
   }

   @Bean
   public WebSecurityCustomizer webSecurityCustomizer()
   {
      return (web) -> web.ignoring()
            .requestMatchers(PUBLIC_MATCHERS)
            .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET);
   }

   // (2)
   @Bean
   public CorsConfigurationSource corsConfigurationSource()
   {
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
      return source;
   }

   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder()
   {
      return new BCryptPasswordEncoder();
   }
}