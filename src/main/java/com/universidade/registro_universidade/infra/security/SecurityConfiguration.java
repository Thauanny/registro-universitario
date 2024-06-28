package com.universidade.registro_universidade.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    throws Exception {
    return httpSecurity
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .authorizeHttpRequests(authorize ->
        authorize
          .requestMatchers(HttpMethod.PUT, "/professor/deleteLogic")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.PUT, "/professor")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.DELETE, "/professor")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.PUT, "/aluno/deleteLogic")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.PUT, "/aluno")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.DELETE, "/aluno")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.POST, "/turma/register")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.PUT, "/turma/addProfessor")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.PUT, "/turma/addAluno")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.PUT, "/turma/deleteLogic")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.PUT, "/turma")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.DELETE, "/turma")
          .hasRole("ADMIN")
          .requestMatchers(HttpMethod.POST, "/auth/login")
          .permitAll()
          .requestMatchers(HttpMethod.POST, "/auth/register")
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration authenticationManager
  ) throws Exception {
    return authenticationManager.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
