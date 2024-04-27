package com.universidade.registro_universidade.model;

import org.hibernate.validator.constraints.br.CPF;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    @Column(name = "nome")
    private String nome;
    @NotNull
    @CPF
    @NotEmpty
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "password")
    private String password;
    @Email
    @NotNull
    @NotEmpty
    @Column(name = "email")
    private String email;
    @NotNull
    @NotEmpty
    @Column(name = "matricula")
    private String matricula;
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @NotNull
    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private GENERO genero;
    @NotNull
    @NotEmpty
    @Column(name = "dataNascimento")
    private String dataNascimento;

}
