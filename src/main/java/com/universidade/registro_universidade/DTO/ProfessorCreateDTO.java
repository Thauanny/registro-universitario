package com.universidade.registro_universidade.DTO;


import com.universidade.registro_universidade.model.Professor;

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

  @NotNull
  @NotEmpty
  private String departamento;
  @NotNull
  private float salario;
  @NotNull
  @NotEmpty
  private String disciplinaAssociada;

  public Professor toEntity() {
    Professor professor = new Professor();
    professor.setNome(this.getNome());
    professor.setEmail(this.getEmail());
    professor.setPassword(this.getPassword());
    professor.setCpf(this.getCpf());
    professor.setDataNascimento(this.getDataNascimento());
    professor.setDisciplinaAssociada(this.getDisciplinaAssociada());
    professor.setSalario(this.getSalario());
    professor.setDepartamento(this.getDepartamento());
    professor.setGenero(this.getGenero());
    professor.setMatricula(this.getMatricula());
    return professor;
  }

}
