package com.universidade.registro_universidade.service;

import com.universidade.registro_universidade.repository.AlunoRepository;
import com.universidade.registro_universidade.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

  @Autowired
  private AlunoRepository alunoRepository;

  @Autowired
  private ProfessorRepository professorRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    UserDetails aluno = alunoRepository.findByEmail(username);
    if (aluno != null) {
      return aluno;
    }
    UserDetails professor = professorRepository.findByEmail(username);
    if (professor != null) {
      return professor;
    }
    throw new UsernameNotFoundException(
      "Usuário não encontrado com o email: " + username
    );
  }
}
