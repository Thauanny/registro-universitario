package com.universidade.registro_universidade.service;

import com.universidade.registro_universidade.DTO.AlunoDTO;
import com.universidade.registro_universidade.model.Aluno;
import com.universidade.registro_universidade.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private AlunoRepository alunoRepository;

  public Aluno aluno(Integer id) {
    Optional<Aluno> alunoOptional = alunoRepository.findByIdWhere(id);
    if (alunoOptional.isPresent()) {
      return alunoOptional.get();
    } else {
      throw new EntityNotFoundException(
        "Aluno não encontrado para o ID: " + id
      );
    }
  }

  public List<Aluno> alunos() {
    return alunoRepository.findAllWhere();
  }

  public List<Aluno> alunosAtivosNaoAtivos() {
    return alunoRepository.findAll();
  }

  public Aluno save(AlunoDTO aluno) {

    Aluno newAluno = new Aluno();
    newAluno.setUsername(aluno.getUsername());
    newAluno.setPassword(passwordEncoder.encode(aluno.getPassword()));
    newAluno.setEmail(aluno.getEmail());
    newAluno.setCpf(aluno.getCpf());
    newAluno.setAtivo(true);
    return alunoRepository.save(newAluno);
  }

  public Aluno update(AlunoDTO aluno, Integer id) {
    Aluno responseAluno = aluno(id);
    responseAluno.setUsername(aluno.getUsername());
    responseAluno.setPassword(passwordEncoder.encode(aluno.getPassword()));
    responseAluno.setEmail(aluno.getEmail());
    responseAluno.setCpf(aluno.getCpf());
    responseAluno.setAtivo(aluno.isAtivo());
    return alunoRepository.save(responseAluno);
  }

  public void delete(Integer id) {
    alunoRepository.deleteById(id);
  }

  public Aluno deleteLogic(Integer id) {
    Aluno _aluno = aluno(id);
    _aluno.setAtivo(false);
   return alunoRepository.save(_aluno);
  }
}
