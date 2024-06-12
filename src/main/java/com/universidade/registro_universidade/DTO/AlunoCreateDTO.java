package com.universidade.registro_universidade.DTO;

import java.util.List;

import com.universidade.registro_universidade.model.Aluno;
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
public class AlunoCreateDTO extends PessoaCreateDTO {

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String curso;

  private List<Turma> turmas;

  public Aluno toEntity() {
    Aluno aluno = new Aluno();
    aluno.setNome(this.getNome());
    aluno.setEmail(this.getEmail());
    aluno.setPassword(this.getPassword());
    aluno.setCpf(this.getCpf());
    aluno.setDataNascimento(this.getDataNascimento());
    aluno.setCurso(this.getCurso());
    aluno.setGenero(this.getGenero());
    aluno.setMatricula(this.getMatricula());
    return aluno;
  }


}
