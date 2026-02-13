package com.utp.technology.web.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

  @Email(message = "Debe ser un formato de correo electrónico válido")
  @NotBlank(message = "El correo es obligatorio")
  private String email;

  @NotBlank(message = "La contraseña es obligatoria")
  private String password;

  public LoginRequestDTO() {}

  public LoginRequestDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
}

