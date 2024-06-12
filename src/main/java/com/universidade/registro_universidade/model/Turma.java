package com.universidade.registro_universidade.model;

import java.util.List;
import java.util.stream.Collectors;
import com.universidade.registro_universidade.DTO.TurmaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    @Column(name = "nome")
    private String nome;

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    @Column(name = "codigo")
    private String codigo;

    @NotNull(message = "O valor não pode ser vazio")
    @Column(name = "ativo")
    private boolean ativo;

    @ManyToMany
    @JoinTable(name = "aluno_turma", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunos;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    public TurmaDTO toDTO() {
        TurmaDTO turma = new TurmaDTO();

        try {
            turma.setId(this.getId());
            turma.setAtivo(this.isAtivo());
            turma.setCodigo(this.getCodigo());
            turma.setNome(this.getNome());
           
            if (this.getAlunos() != null && !this.getAlunos().isEmpty()) {
                turma.setAlunos(this.getAlunos().stream().map(Aluno::toDTO).collect(Collectors.toList()));
            }
            if (this.getProfessor() != null) {
                turma.setProfessor(this.getProfessor().toDTO());
            }
            return turma;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Entity em DTO", e);
        }

    }

}