package com.universidade.registrouniversitario.model;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
	private int aluno_id;
    @NotNull
    @NotEmpty
    @Column(name = "username")
	private String username;
    @NotNull
    @CPF
    @NotEmpty
    @Column(name = "cpf")
    private String cpf;
    @NotNull
    @NotEmpty
    @Column(name = "password")
	private String password;
    @Email
    @NotNull
    @NotEmpty
    @Column(name = "email")
	private String email;
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;

}