package com.universidade.registro_universidade.infra.security;

import com.universidade.registro_universidade.repository.AlunoRepository;
import com.universidade.registro_universidade.repository.ProfessorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  TokenService tokenService;

  @Autowired
  AlunoRepository alunoRepository;

  @Autowired
  ProfessorRepository professorRepository;

  @Override
  protected void doFilterInternal(
    @SuppressWarnings("null") HttpServletRequest request,
    @SuppressWarnings("null") HttpServletResponse response,
    @SuppressWarnings("null") FilterChain filterChain
  ) throws IOException, ServletException {
    var token = this.recoverToken(request);
    if (token != null) {
      var email = tokenService.validateToken(token);
      UserDetails user;
      if (alunoRepository.findByEmail(email) != null) {
        user = alunoRepository.findByEmail(email);
      } else {
        user = professorRepository.findByEmail(email);
      }

      var authentication = new UsernamePasswordAuthenticationToken(
        user,
        null,
        user.getAuthorities()
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null) return null;
    return authHeader.replace("Bearer ", "");
  }
}
