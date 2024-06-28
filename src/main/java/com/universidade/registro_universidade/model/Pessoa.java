package com.universidade.registro_universidade.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
@Getter
@Setter
public class Pessoa implements UserDetails {

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
  @CPF
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

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private ROLE role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == ROLE.ADMIN) return List.of(
      new SimpleGrantedAuthority("ROLE_ADMIN"),
      new SimpleGrantedAuthority("ROLE_USER")
    ); else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
