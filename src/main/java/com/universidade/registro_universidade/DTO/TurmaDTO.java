package com.universidade.registro_universidade.DTO;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.hibernate.internal.ExceptionConverterImpl;

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
public class TurmaDTO {

    @NotNull(message = "O valor não pode ser vazio")
    private int id;

    private ProfessorDTO professor;

    private List<AlunoDTO> alunos;

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    private String codigo;

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    private String nome;

    @NotNull(message = "O valor não pode ser vazio")
    private boolean ativo;

    public Turma toEntity() {
        try {
            Turma turma = new Turma();
            turma.setId(this.getId());
            turma.setNome(this.getNome());
            turma.setAtivo(this.isAtivo());
            turma.setCodigo(this.getCodigo());
            if (this.getAlunos() != null && !this.getAlunos().isEmpty()) {
                turma.setAlunos(this.getAlunos().stream().map(AlunoDTO::toEntity).collect(Collectors.toList()));

            }
            if (this.getProfessor() != null) {
                turma.setProfessor(this.getProfessor().toEntity());
            }
            return turma;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter DTO em Entity", e);
        }

    }
}
