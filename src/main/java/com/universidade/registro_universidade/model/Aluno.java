package com.universidade.registro_universidade.model;

import com.universidade.registro_universidade.DTO.AlunoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "aluno")
public class Aluno extends Pessoa {
    @NotNull
    @NotEmpty
    @Column(name = "curso")
    private String curso;

    public AlunoDTO toDTO() {
        AlunoDTO aluno = new AlunoDTO();
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
    }
}