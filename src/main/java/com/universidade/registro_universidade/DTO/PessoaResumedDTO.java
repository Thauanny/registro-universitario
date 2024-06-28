package com.universidade.registro_universidade.DTO;

import com.universidade.registro_universidade.model.Aluno;
import com.universidade.registro_universidade.model.GENERO;
import com.universidade.registro_universidade.model.PESSOATYPE;
import com.universidade.registro_universidade.model.Professor;
import com.universidade.registro_universidade.model.ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
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
public class PessoaResumedDTO {

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private int id;

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String nome;

  @Email(message = "Email invalido")
  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String email;

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String matricula;

  @Enumerated(EnumType.STRING)
  private GENERO genero;

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String dataNascimento;

  @NotNull(message = "O valor não pode ser vazio")
  private ROLE role;

  @NotNull(message = "O valor não pode ser vazio")
  @Enumerated(EnumType.STRING)
  private PESSOATYPE type;

  public Aluno toAlunoEntity() {
    Aluno aluno = new Aluno();
    try {
      aluno.setId(this.getId());
      aluno.setNome(this.getNome());
      aluno.setEmail(this.getEmail());
      aluno.setDataNascimento(this.getDataNascimento());
      aluno.setGenero(this.getGenero());
      aluno.setMatricula(this.getMatricula());
      aluno.setRole(this.getRole());
      aluno.setType(this.getType());
      return aluno;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter DTO em Entity", e);
    }
  }

  Professor toProfessorEntity() {
    Professor professor = new Professor();
    try {
      professor.setId(this.getId());
      professor.setNome(this.getNome());
      professor.setEmail(this.getEmail());
      professor.setDataNascimento(this.getDataNascimento());
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
