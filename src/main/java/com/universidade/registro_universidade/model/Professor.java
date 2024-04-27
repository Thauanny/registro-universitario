package com.universidade.registro_universidade.model;

import com.universidade.registro_universidade.DTO.ProfessorDTO;

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
@Table(name = "professor")
public class Professor extends Pessoa {
    @NotNull
    @NotEmpty
    @Column(name = "departamento")
    private String departamento;
    @NotNull
    @Column(name = "salario")
    private float salario;
    @NotNull
    @NotEmpty
    @Column(name = "disciplinaAssociada")
    private String disciplinaAssociada;

    public ProfessorDTO toDTO() {
        ProfessorDTO professor = new ProfessorDTO();
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
    }
}