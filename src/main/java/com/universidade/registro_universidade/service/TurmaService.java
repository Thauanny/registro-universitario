package com.universidade.registro_universidade.service;

import com.universidade.registro_universidade.DTO.TurmaCreateDTO;
import com.universidade.registro_universidade.DTO.TurmaDTO;
import com.universidade.registro_universidade.model.Aluno;
import com.universidade.registro_universidade.model.Professor;
import com.universidade.registro_universidade.model.Turma;
import com.universidade.registro_universidade.repository.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

  @Autowired
  private TurmaRepository turmaRepository;

  @Autowired
  private ProfessorService professorService;

  @Autowired
  private AlunoService alunoService;

  public TurmaDTO turma(Integer id) {
    Optional<Turma> turmaOptional = turmaRepository.findByIdWhere(id);
    if (turmaOptional.isPresent()) {
      return turmaOptional.get().toDTO();
    } else {
      throw new EntityNotFoundException(
          "Turma não encontrado para o ID: " + id);
    }
  }

  public List<TurmaDTO> turmaAtivosNaoAtivos() {
    List<TurmaDTO> turmaDTOs = new ArrayList<>();
    List<Turma> turmas = turmaRepository.findAll();
    for (int i = 0; i < turmas.size(); i++) {
      turmaDTOs.add(turmas.get(i).toDTO());
    }
    return turmaDTOs;
  }

  public TurmaDTO save(TurmaCreateDTO turma) {
    Turma turmaEntity = turma.toEntity();
    turmaEntity.setAtivo(true);
    return turmaRepository.save(turmaEntity).toDTO();
  }

  public TurmaDTO update(TurmaDTO turma, Integer id) {
    TurmaDTO responseTurma = turma(id);
    if (turma.getNome() != null && !(turma.getNome().isEmpty())) {
      responseTurma.setNome(turma.getNome());
    }
    if (turma.getAlunos() != null && !(turma.getAlunos().isEmpty())) {
      responseTurma.setAlunos(turma.getAlunos());
    }
    if (turma.getCodigo() != null && !(turma.getCodigo().isEmpty())) {
      responseTurma.setCodigo(turma.getCodigo());
    }
    if (turma.getProfessor() != null) {
      responseTurma.setProfessor(turma.getProfessor());
    }

    return turmaRepository.save(responseTurma.toEntity()).toDTO();
  }

  public void delete(Integer id) {
    turmaRepository.deleteById(id);
  }

  public TurmaDTO registerProfessorOnTurma(Integer id, Integer idProfessor) {
    try {
      Turma turma = turma(id).toEntity();
      Professor professor = professorService.professor(idProfessor).toEntity();
      turma.setProfessor(professor);
      return turmaRepository.save(turma).toDTO();
    } catch (Exception e) {
      throw e;
    }

  }

  public TurmaDTO registerAlunoOnTurma(Integer id, Integer idAluno) {
    try {
      Turma turma = turma(id).toEntity();
      Aluno aluno = alunoService.aluno(idAluno).toEntity();
      List<Aluno> alunos = new ArrayList<>();
      if (turma.getAlunos() != null && !(turma.getAlunos().isEmpty())) {
        turma.getAlunos().add(aluno);
        turma.setAlunos(turma.getAlunos());
      } else {
        turma.setAlunos(alunos);
        turma.getAlunos().add(aluno);
        turma.setAlunos(turma.getAlunos());
      }

      return turmaRepository.save(turma).toDTO();
    } catch (Exception e) {
      throw e;
    }

  }

  public TurmaDTO deleteLogic(Integer id) {
    TurmaDTO _turma = turma(id);
    _turma.setAtivo(false);
    return turmaRepository.save(_turma.toEntity()).toDTO();
  }

  public TurmaDTO removerProfessor(Integer id, Integer idProfessor) {
    try {
      Turma turma = turma(id).toEntity();
      turma.setProfessor(null);
      return turmaRepository.save(turma).toDTO();
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(
          "Professor com ID: " + id + " não encontrado na turma");
    } catch (Exception e) {
      throw e;
    }
  }

  public TurmaDTO removerAluno(Integer id, Integer idAluno) {
    try {
      Turma turma = turma(id).toEntity();
      List<Aluno> _alunos = new ArrayList<>();
      _alunos = turma.getAlunos();
      _alunos.removeIf(aluno -> aluno.getId() == idAluno);
      turma.setAlunos(_alunos);
      return turmaRepository.save(turma).toDTO();
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(
          "Aluno com ID: " + id + " não encontrado na turma");
    } catch (Exception e) {
      throw e;
    }
  }
}
