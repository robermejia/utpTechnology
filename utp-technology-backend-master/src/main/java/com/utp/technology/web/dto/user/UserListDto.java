package com.utp.technology.web.dto.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "nombre", "correo" })
public class UserListDto {

  private Integer id;
  private String nombre;
  private String correo;

  public UserListDto() {}

  public UserListDto(Integer id, String nombre, String correo) {
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
  }

  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getCorreo() { return correo; }
  public void setCorreo(String correo) { this.correo = correo; }
}

