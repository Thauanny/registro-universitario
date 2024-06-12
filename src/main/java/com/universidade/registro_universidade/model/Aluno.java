package com.universidade.registro_universidade.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.universidade.registro_universidade.DTO.AlunoDTO;
import com.universidade.registro_universidade.DTO.PessoaResumedDTO;
import com.universidade.registro_universidade.DTO.TurmaResumedDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    @Column(name = "curso")
    private String curso;

    @ManyToMany(mappedBy = "alunos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"turmas", "alunos" })
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
             if (this.getTurmas() != null && !this.getTurmas().isEmpty()) {
                aluno.setTurmas(this.getTurmas().stream().map(Turma::toResumedDTO).collect(Collectors.toList()));

            }
            return aluno;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Entity em DTO", e);
        }

    }

    public PessoaResumedDTO toResumedPessoaDTO() {
    PessoaResumedDTO aluno = new PessoaResumedDTO();
    try {
      aluno.setId(this.getId());
      aluno.setNome(this.getNome());
      aluno.setEmail(this.getEmail());
      aluno.setDataNascimento(this.getDataNascimento());
      aluno.setGenero(this.getGenero());
      aluno.setMatricula(this.getMatricula());
      return aluno;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter DTO em Entity", e);
    }
   
  }
    

}