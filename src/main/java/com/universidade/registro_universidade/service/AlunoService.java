package com.universidade.registro_universidade.service;

import com.universidade.registro_universidade.DTO.AlunoCreateDTO;
import com.universidade.registro_universidade.DTO.AlunoDTO;
import com.universidade.registro_universidade.DTO.ProfessorDTO;
import com.universidade.registro_universidade.model.Aluno;
import com.universidade.registro_universidade.model.Professor;
import com.universidade.registro_universidade.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
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

  public AlunoDTO aluno(Integer id) {
    Optional<Aluno> alunoOptional = alunoRepository.findByIdWhere(id);
    if (alunoOptional.isPresent()) {
      return alunoOptional.get().toDTO();
    } else {
      throw new EntityNotFoundException(
          "Aluno não encontrado para o ID: " + id);
    }
  }

  public List<AlunoDTO> alunos() {
    List<AlunoDTO> alunoDTOs = new ArrayList<>();
    List<Aluno> alunos = alunoRepository.findAllWhere();
    for (int i = 0; i < alunos.size(); i++) {
      alunoDTOs.add(alunos.get(i).toDTO()); 
    }
    return alunoDTOs;
  }

  public List<AlunoDTO> alunosAtivosNaoAtivos() {
    List<AlunoDTO> alunoDTOs = new ArrayList<>();
    List<Aluno> alunos = alunoRepository.findAll();
    for (int i = 0; i < alunos.size(); i++) {
      alunoDTOs.add(alunos.get(i).toDTO()); 
    }
    return alunoDTOs;
  }

  public AlunoDTO register(AlunoCreateDTO aluno) {
    aluno.setPassword(passwordEncoder.encode(aluno.getPassword()));
    Aluno alunoEntity = aluno.toEntity();
    alunoEntity.setAtivo(true);
    return alunoRepository.save(alunoEntity).toDTO();
  }

   public AlunoDTO save(Aluno aluno) {
      return alunoRepository.save(aluno).toDTO();
  }

  public AlunoDTO update(AlunoDTO aluno, Integer id) {
    AlunoDTO responseAluno = aluno(id);
    if(aluno.getNome() != null && !(aluno.getNome().isEmpty())){
      responseAluno.setNome(aluno.getNome());
    }
    if(aluno.getEmail() != null && !(aluno.getEmail().isEmpty())){
      responseAluno.setEmail(aluno.getEmail());
    }
    if(aluno.getCpf() != null && !(aluno.getCpf().isEmpty())){
      responseAluno.setCpf(aluno.getCpf());
    }
    if(aluno.getCurso() != null && !(aluno.getCurso().isEmpty())){
      responseAluno.setCurso(aluno.getCurso());
    }
    if(aluno.getMatricula() != null && !(aluno.getMatricula().isEmpty())){
      responseAluno.setMatricula(aluno.getMatricula());
    } 
    if(aluno.getGenero() != null){
      responseAluno.setGenero(aluno.getGenero());
    }
    if(aluno.getDataNascimento() != null && !(aluno.getDataNascimento().isEmpty())){
      responseAluno.setDataNascimento(aluno.getDataNascimento());
    } 
    return alunoRepository.save(responseAluno.toEntity()).toDTO();
  }

  public void delete(Integer id) {
    alunoRepository.deleteById(id);
  }

  public AlunoDTO deleteLogic(Integer id) {
    AlunoDTO _aluno = aluno(id);
    _aluno.setAtivo(false);
    return alunoRepository.save(_aluno.toEntity()).toDTO();
  }
}
