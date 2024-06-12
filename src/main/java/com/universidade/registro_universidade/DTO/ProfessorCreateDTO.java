package com.universidade.registro_universidade.DTO;

import java.util.ArrayList;
import java.util.List;

import com.universidade.registro_universidade.model.Professor;
import com.universidade.registro_universidade.model.Turma;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessorCreateDTO extends PessoaCreateDTO {

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String departamento;

  @NotNull(message = "O valor não pode ser vazio")
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
      return professor;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter DTO em Entity", e);
    }

  }

}
