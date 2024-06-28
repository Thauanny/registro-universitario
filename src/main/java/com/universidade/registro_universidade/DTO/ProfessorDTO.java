package com.universidade.registro_universidade.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.universidade.registro_universidade.model.Professor;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessorDTO extends PessoaDTO {

  private String departamento;

  private float salario;

  @JsonIgnoreProperties({ "professor" })
  private List<TurmaResumedDTO> turmas = new ArrayList<>();

  public Professor toEntity() {
    Professor professor = new Professor();
    try {
      professor.setId(this.getId());
      professor.setNome(this.getNome());
      professor.setEmail(this.getEmail());
      professor.setCpf(this.getCpf());
      professor.setAtivo(this.isAtivo());
      professor.setDataNascimento(this.getDataNascimento());
      professor.setSalario(this.getSalario());
      professor.setDepartamento(this.getDepartamento());
      professor.setGenero(this.getGenero());
      professor.setMatricula(this.getMatricula());
      professor.setRole(this.getRole());
      professor.setType(this.getType());
      return professor;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter DTO em Entity", e);
    }
  }
}
