package com.universidade.registro_universidade.service;

import com.universidade.registro_universidade.DTO.ProfessorCreateDTO;
import com.universidade.registro_universidade.DTO.ProfessorDTO;
import com.universidade.registro_universidade.model.Professor;
import com.universidade.registro_universidade.repository.ProfessorRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private ProfessorRepository professorRepository;

  public ProfessorDTO professor(Integer id) {
    Optional<Professor> alunoOptional = professorRepository.findByIdWhere(id);
    if (alunoOptional.isPresent()) {
      return alunoOptional.get().toDTO();
    } else {
      throw new EntityNotFoundException(
          "Professor não encontrado para o ID: " + id);
    }
  }

  public List<ProfessorDTO> professores() {
    List<ProfessorDTO> alunoDTOs = new ArrayList<>();
    List<Professor> alunos = professorRepository.findAllWhere();
    for (int i = 0; i < alunos.size(); i++) {
      alunoDTOs.add(alunos.get(i).toDTO()); 
    }
    return alunoDTOs;
  }

  public List<ProfessorDTO> professorAtivosNaoAtivos() {
    List<ProfessorDTO> alunoDTOs = new ArrayList<>();
    List<Professor> alunos = professorRepository.findAll();
    for (int i = 0; i < alunos.size(); i++) {
      alunoDTOs.add(alunos.get(i).toDTO()); 
    }
    return alunoDTOs;
  }

  public ProfessorDTO register(ProfessorCreateDTO professor) {
    professor.setPassword(passwordEncoder.encode(professor.getPassword()));
    Professor professorEntity = professor.toEntity();
    professorEntity.setAtivo(true);
      return professorRepository.save(professorEntity).toDTO();
  }

  public ProfessorDTO save(Professor professor) {
      return professorRepository.save(professor).toDTO();
  }

  public ProfessorDTO update(ProfessorDTO professor, Integer id) {
    ProfessorDTO responseProfessor = professor(id);
    if(professor.getNome() != null && !(professor.getNome().isEmpty())){
      responseProfessor.setNome(professor.getNome());
    }
    if(professor.getEmail() != null && !(professor.getEmail().isEmpty())){
      responseProfessor.setEmail(professor.getEmail());
    }
    if(professor.getCpf() != null && !(professor.getCpf().isEmpty())){
      responseProfessor.setCpf(professor.getCpf());
    }
    if(professor.getDepartamento() != null && !(professor.getDepartamento().isEmpty())){
      responseProfessor.setDepartamento(professor.getDepartamento());
    }
    if(professor.getTurmas() != null && !(professor.getTurmas().isEmpty())){
      responseProfessor.setTurmas(professor.getTurmas());
    } 
    if(professor.getSalario() != 0){
      responseProfessor.setSalario(professor.getSalario());
    }
    if(professor.getMatricula() != null && !(professor.getMatricula().isEmpty())){
      responseProfessor.setMatricula(professor.getMatricula());
    } 
    if(professor.getGenero() != null){
      responseProfessor.setGenero(professor.getGenero());
    }
    if(professor.getDataNascimento() != null && !(professor.getDataNascimento().isEmpty())){
      responseProfessor.setDataNascimento(professor.getDataNascimento());
    } 
    return professorRepository.save(responseProfessor.toEntity()).toDTO();
  }

  public void delete(Integer id) {
    professorRepository.deleteById(id);
  }

  public ProfessorDTO deleteLogic(Integer id) {
    ProfessorDTO _professor = professor(id);
    _professor.setAtivo(false);
    return professorRepository.save(_professor.toEntity()).toDTO();
  }
}
