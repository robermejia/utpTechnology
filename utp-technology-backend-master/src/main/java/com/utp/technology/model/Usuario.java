package com.utp.technology.model;

public class Usuario {

  private Integer id;
  private String nombre;
  private String correo;
  private String clave;
  private Integer idRol;

  public Usuario() {}

  public Usuario(Integer id, String nombre, String correo, String clave, Integer idRol) {
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
    this.clave = clave;
    this.idRol = idRol;
  }

  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getCorreo() { return correo; }
  public void setCorreo(String correo) { this.correo = correo; }

  public String getClave() { return clave; }
  public void setClave(String clave) { this.clave = clave; }

  public Integer getIdRol() { return idRol; }
  public void setIdRol(Integer idRol) { this.idRol = idRol; }
}

