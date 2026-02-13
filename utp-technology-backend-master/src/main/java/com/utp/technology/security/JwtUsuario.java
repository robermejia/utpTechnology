package com.utp.technology.security;

public class JwtUsuario {

  private Integer id;
  private Integer rolId;
  private String correo;

  public JwtUsuario() {}

  public JwtUsuario(Integer id, Integer rolId, String correo) {
    this.id = id;
    this.rolId = rolId;
    this.correo = correo;
  }

  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }

  public Integer getRolId() { return rolId; }
  public void setRolId(Integer rolId) { this.rolId = rolId; }

  public String getCorreo() { return correo; }
  public void setCorreo(String correo) { this.correo = correo; }
}

