package com.universidade.registro_universidade.DTO;

import com.universidade.registro_universidade.model.Aluno;
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

  @NotNull
  @NotEmpty
  private String curso;

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
