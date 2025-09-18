package al.oxetech.projeto_final.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe de configuração de segurança.
 *
 * <p>Manter as configurações de segurança centralizadas facilita o controle
 * de como as requisições são tratadas e como as senhas são codificadas.
 * Mesmo que neste exemplo todas as rotas estejam liberadas, em um sistema real
 * este arquivo definiria regras de autenticação e autorização.</p>
 */
@Configuration
public class SecurityConfig {

    public  final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter){
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * Bean responsável por criptografar as senhas usando o algoritmo BCrypt.
     * O retorno deste bean é injetado onde for necessário codificar senhas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuração de como o Spring Security irá proteger as requisições HTTP.
     * Aqui optamos por permitir todas as requisições e desabilitar CSRF para
     * simplificar o exemplo.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/chamados").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/chamados").authenticated()
                        .requestMatchers("/chamados/{chamadoId}/**").hasAnyRole("ADMIN", "SUPORTE")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
