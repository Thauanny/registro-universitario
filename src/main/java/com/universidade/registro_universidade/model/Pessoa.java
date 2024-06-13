package com.universidade.registro_universidade.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
@Getter
@Setter
public class Pessoa {

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
    @CPF()
    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "password")
    private String password;
    
    @Email(message = "Email invalido")
    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    @Column(name = "matricula")
    private String matricula;

    @Column(name = "ativo")
    private boolean ativo;

    @NotNull(message = "O valor não pode ser vazio")
    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private GENERO genero;

    @NotNull(message = "O valor não pode ser vazio")
    @NotEmpty(message = "O valor não pode ser em branco")
    @Column(name = "dataNascimento")
    private String dataNascimento;

}
