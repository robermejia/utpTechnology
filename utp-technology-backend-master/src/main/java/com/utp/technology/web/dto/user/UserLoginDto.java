package com.utp.technology.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.utp.technology.model.Usuario;

@JsonPropertyOrder({ "id", "id_rol", "correo", "nombre" })
public class UserLoginDto {

  private Integer id;

  @JsonProperty("id_rol")
  private Integer idRol;

  private String correo;

  private String nombre;

  public UserLoginDto() {
  }

  public UserLoginDto(Integer id, Integer idRol, String correo, String nombre) {
    this.id = id;
    this.idRol = idRol;
    this.correo = correo;
    this.nombre = nombre;
  }

  public UserLoginDto(Usuario usuario) {
    this.id = usuario.getId();
    this.idRol = usuario.getId_rol();
    this.correo = usuario.getCorreo();
    this.nombre = usuario.getNombre();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIdRol() {
    return idRol;
  }

  public void setIdRol(Integer idRol) {
    this.idRol = idRol;
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
