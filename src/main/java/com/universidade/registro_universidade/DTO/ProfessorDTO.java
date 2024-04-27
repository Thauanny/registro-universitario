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
public class ProfessorDTO extends PessoaDTO {

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
    professor.setId(this.getId());
    professor.setNome(this.getNome());
    professor.setEmail(this.getEmail());
    professor.setCpf(this.getCpf());
    professor.setAtivo(this.isAtivo());
    professor.setDataNascimento(this.getDataNascimento());
    professor.setSalario(this.getSalario());
    professor.setDepartamento(this.getDepartamento());
    professor.setDisciplinaAssociada(this.getDisciplinaAssociada());
    professor.setGenero(this.getGenero());
    professor.setMatricula(this.getMatricula());
    return professor;
  }
}
