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
public class TurmaResumedDTO {

    @NotNull(message = "O valor não pode ser vazio")
    private int id;

    private PessoaResumedDTO professor;

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    private String codigo;

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    private String nome;

    public Turma toEntity() {
        try {
            Turma turma = new Turma();
            turma.setId(this.getId());
            turma.setNome(this.getNome());
            turma.setCodigo(this.getCodigo());
            
            if (this.getProfessor() != null) {
                turma.setProfessor(this.getProfessor().toProfessorEntity());
            }
            return turma;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter DTO em Entity", e);
        }

    }
}
