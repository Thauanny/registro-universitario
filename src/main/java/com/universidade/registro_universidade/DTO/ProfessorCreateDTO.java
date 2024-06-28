package com.universidade.registro_universidade.DTO;

import com.universidade.registro_universidade.model.Professor;
import com.universidade.registro_universidade.model.Turma;
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
public class ProfessorCreateDTO extends PessoaCreateDTO {

  private String departamento;

  private float salario;

  private List<Turma> turmas = new ArrayList<>();

  public Professor toEntity() {
    Professor professor = new Professor();
    try {
      professor.setNome(this.getNome());
      professor.setEmail(this.getEmail());
      professor.setPassword(this.getPassword());
      professor.setCpf(this.getCpf());
      professor.setDataNascimento(this.getDataNascimento());
      professor.setTurmas(this.getTurmas());
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
