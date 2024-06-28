package com.universidade.registro_universidade.DTO;

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
public class TurmaCreateDTO {

  @NotNull(message = "O valor não pode ser vazio")
  private int id;

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String codigo;

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String nome;

  @NotNull(message = "O valor não pode ser vazio")
  private boolean ativo;

  public Turma toEntity() {
    Turma turma = new Turma();
    try {
      turma.setId(this.getId());
      turma.setNome(this.getNome());
      turma.setAtivo(this.isAtivo());
      turma.setCodigo(this.getCodigo());
      return turma;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter DTO em Entity", e);
    }
  }
}
