package com.universidade.registro_universidade.DTO;

import com.universidade.registro_universidade.model.GENERO;
import com.universidade.registro_universidade.model.PESSOATYPE;
import com.universidade.registro_universidade.model.ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private int id;

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String nome;

  @CPF(message = "CPF invalido")
  private String cpf;

  @Email(message = "Email invalido")
  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String email;

  @NotNull(message = "O valor não pode ser vazio")
  private boolean ativo;

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String matricula;

  @Enumerated(EnumType.STRING)
  private GENERO genero;

  @NotNull(message = "O valor não pode ser vazio")
  @NotEmpty(message = "O valor não pode ser em branco")
  private String dataNascimento;

  @NotNull(message = "O valor não pode ser vazio")
  private ROLE role;

  @NotNull(message = "O valor não pode ser vazio")
  @Enumerated(EnumType.STRING)
  private PESSOATYPE type;
}
