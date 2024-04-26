package com.universidade.registro_universidade.DTO;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoDTO {

    @NotNull
    @NotEmpty
	private String username;
    @NotNull
    @CPF
    @NotEmpty
    private String cpf;
    @NotNull
    @NotEmpty
	private String password;
    @Email
    @NotNull
    @NotEmpty
	private String email;
    @NotNull
    private boolean ativo;
}