package com.utp.technology.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "nombre", "correo", "idRol" })
public class UserListDto {

  private Integer id;
  private String nombre;
  private String correo;
  @JsonProperty("id_rol")
  private Integer idRol;

  public UserListDto() {
  }

  public UserListDto(Integer id, String nombre, String correo, Integer idRol) {
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
    this.idRol = idRol;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public Integer getIdRol() {
    return idRol;
  }

  public void setIdRol(Integer idRol) {
    this.idRol = idRol;
  }
}
