package com.universidade.registro_universidade.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
      .authorizeHttpRequests(authorize -> authorize
      .requestMatchers(HttpMethod.POST, "professor/register" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "professor/deleteLogic" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "professor" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.DELETE, "professor" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.POST, "aluno/register" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "aluno/deleteLogic" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "aluno" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.DELETE, "aluno" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.POST, "turma/register" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "turma/addProfessor" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "turma/addAluno" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "turma/deleteLogic" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "turma" ).hasRole("ADMIN")
      .requestMatchers(HttpMethod.DELETE, "turma" ).hasRole("ADMIN")
      .anyRequest().authenticated()
      )
      .build();
  }
}
