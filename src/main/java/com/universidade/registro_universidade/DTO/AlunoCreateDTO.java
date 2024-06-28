package com.universidade.registro_universidade.DTO;

import com.universidade.registro_universidade.model.Aluno;
import com.universidade.registro_universidade.model.Turma;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoCreateDTO extends PessoaCreateDTO {

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
    aluno.setRole(this.getRole());
    aluno.setType(this.getType());
    return aluno;
  }
}
