package org.example.lista_de_compras_back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desactiva CSRF si no usas formularios
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Permite todas las rutas sin autenticación
                )
                .formLogin(login -> login.disable()) // Desactiva el formulario de login
                .httpBasic(basic -> basic.disable()); // Desactiva autenticación básica

        return http.build();
    }
}

