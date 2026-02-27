package com.utp.technology.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.utp.technology.model.Usuario;

@JsonPropertyOrder({ "id", "id_rol", "correo", "nombre" })
public class UserLoginDto {

  private Integer id;

  @JsonProperty("id_rol")
  private Integer id_rol;

  private String correo;

  private String nombre;

  public UserLoginDto() {
  }

  public UserLoginDto(Integer id, Integer id_rol, String correo, String nombre) {
    this.id = id;
    this.id_rol = id_rol;
    this.correo = correo;
    this.nombre = nombre;
  }

  public UserLoginDto(Usuario usuario) {
    this.id = usuario.getId();
    this.id_rol = usuario.getId_rol();
    this.correo = usuario.getCorreo();
    this.nombre = usuario.getNombre();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId_rol() {
    return id_rol;
  }

  public void setId_rol(Integer id_rol) {
    this.id_rol = id_rol;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
