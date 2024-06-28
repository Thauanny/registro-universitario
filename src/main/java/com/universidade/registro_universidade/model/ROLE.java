package com.universidade.registro_universidade.model;

public enum ROLE {
  ADMIN("admin"),
  USER("user");

  private String role;

  ROLE(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
