package com.universidade.registro_universidade.model;

import com.universidade.registro_universidade.DTO.ProfessorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "professor")
public class Professor extends Pessoa {

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @Column(name = "salario", nullable = false)
    private float salario;

    @Column(name = "disciplinaAssociada", nullable = false)
    private String disciplinaAssociada;

    public ProfessorDTO toDTO() {
        ProfessorDTO professor = new ProfessorDTO();

        try {
            professor.setId(this.getId());
            professor.setNome(this.getNome());
            professor.setEmail(this.getEmail());
            professor.setCpf(this.getCpf());
            professor.setAtivo(this.isAtivo());
            professor.setDataNascimento(this.getDataNascimento());
            professor.setSalario(this.getSalario());
            professor.setDepartamento(this.getDepartamento());
            professor.setDisciplinaAssociada(this.getDisciplinaAssociada());
            professor.setGenero(this.getGenero());
            professor.setMatricula(this.getMatricula());
            return professor;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Entity em DTO", e);
        }

    }
}