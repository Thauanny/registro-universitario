package com.universidade.registro_universidade.DTO;

import com.universidade.registro_universidade.model.GENERO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.br.CPF;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @CPF
    @NotEmpty
    private String cpf;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    private boolean ativo;

    @NotNull
    @NotEmpty
    private String matricula;

    @Enumerated(EnumType.STRING)
    private GENERO genero;

    @NotNull
    @NotEmpty
    private String dataNascimento;
}
