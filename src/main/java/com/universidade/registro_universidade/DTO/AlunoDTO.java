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
public class AlunoDTO extends PessoaDTO {

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String curso;

  private List<Turma> turmas;

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
      aluno.setMatricula(this.getMatricula());
      return aluno;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter DTO em Entity", e);
    }
   
  }
}
