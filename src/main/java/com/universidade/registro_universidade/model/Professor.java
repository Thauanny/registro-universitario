package com.universidade.registro_universidade.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.universidade.registro_universidade.DTO.PessoaResumedDTO;
import com.universidade.registro_universidade.DTO.ProfessorDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    @Column(name = "departamento")
    private String departamento;

    @NotNull(message = "O valor não pode ser vazio")
    @Column(name = "salario")
    private float salario;

    @OneToMany(mappedBy = "professor",fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties({ "professor" })
    private List<Turma> turmas = new ArrayList<>();

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
            professor.setGenero(this.getGenero());
            professor.setMatricula(this.getMatricula());
            if (this.getTurmas() != null && !this.getTurmas().isEmpty()) {
                professor.setTurmas(this.getTurmas().stream().map(Turma::toResumedDTO).collect(Collectors.toList()));

            }
            return professor;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Entity em DTO", e);
        }

    }

    public PessoaResumedDTO toResumeDTO() {
        PessoaResumedDTO professor = new PessoaResumedDTO();
        try {
            professor.setId(this.getId());
            professor.setNome(this.getNome());
            professor.setEmail(this.getEmail());
            professor.setDataNascimento(this.getDataNascimento());
            professor.setGenero(this.getGenero());
            professor.setMatricula(this.getMatricula());
            return professor;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter DTO em Entity", e);
        }

    }

}