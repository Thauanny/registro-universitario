package com.universidade.registro_universidade.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.universidade.registro_universidade.model.Aluno;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoDTO extends PessoaDTO {

  private String curso;

  private List<TurmaResumedDTO> turmas = new ArrayList<>();

  public Aluno toEntity() {
    Aluno aluno = new Aluno();
    try {
      aluno.setId(this.getId());
      aluno.setNome(this.getNome());
      aluno.setEmail(this.getEmail());
      aluno.setCpf(this.getCpf());
      aluno.setAtivo(this.isAtivo());
      aluno.setDataNascimento(this.getDataNascimento());
      aluno.setCurso(this.getCurso());
      aluno.setGenero(this.getGenero());
      aluno.setRole(this.getRole());
      aluno.setType(this.getType());
      aluno.setMatricula(this.getMatricula());
      if (this.getTurmas() != null && !this.getTurmas().isEmpty()) {
        aluno.setTurmas(this.getTurmas().stream().map(TurmaResumedDTO::toEntity).collect(Collectors.toList()));

    }
      return aluno;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter DTO em Entity", e);
    }
   
  }

  public PessoaResumedDTO toResumedPessoaDTO() {
    PessoaResumedDTO aluno = new PessoaResumedDTO();
    try {
      aluno.setId(this.getId());
      aluno.setNome(this.getNome());
      aluno.setEmail(this.getEmail());
      aluno.setDataNascimento(this.getDataNascimento());
      aluno.setGenero(this.getGenero());
      aluno.setMatricula(this.getMatricula());
      aluno.setType(this.getType());
      aluno.setRole(this.getRole());
      return aluno;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter DTO em Entity", e);
    }
   
  }
}
