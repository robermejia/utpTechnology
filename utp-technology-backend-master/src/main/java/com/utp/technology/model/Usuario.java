package com.utp.technology.model;

import com.google.cloud.firestore.annotation.PropertyName;

public class Usuario {

  private Integer id;
  private String nombre;
  private String correo;
  private String clave;
  private Integer id_rol;

  public Usuario() {
  }

  public Usuario(Integer id, String nombre, String correo, String clave, Integer id_rol) {
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
    this.clave = clave;
    this.id_rol = id_rol;
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

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  @PropertyName("id_rol")
  public Integer getId_rol() {
    return id_rol;
  }

  @PropertyName("id_rol")
  public void setId_rol(Integer id_rol) {
    this.id_rol = id_rol;
  }
}
