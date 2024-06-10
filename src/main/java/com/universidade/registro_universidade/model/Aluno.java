package com.universidade.registro_universidade.model;

import java.util.List;

import com.universidade.registro_universidade.DTO.AlunoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

    @Column(name = "curso", nullable = false)
    private String curso;

    @ManyToMany(mappedBy = "alunos")
    private List<Turma> turmas;

    public AlunoDTO toDTO() {
        AlunoDTO aluno = new AlunoDTO();

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
            throw new RuntimeException("Erro ao converter Entity em DTO", e);
        }

    }
}