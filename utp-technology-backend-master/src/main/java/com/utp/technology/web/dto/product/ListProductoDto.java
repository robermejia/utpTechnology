package com.utp.technology.web.dto.product;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "nombre", "imagen", "precio", "stock" })
public class ListProductoDto {

  private Integer id;
  private String nombre;
  private String imagen;
  private Double precio;
  private Integer stock;

  public ListProductoDto() {}

  public ListProductoDto(Integer id, String nombre, String imagen, Double precio, Integer stock) {
    this.id = id;
    this.nombre = nombre;
    this.imagen = imagen;
    this.precio = precio;
    this.stock = stock;
  }

  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getImagen() { return imagen; }
  public void setImagen(String imagen) { this.imagen = imagen; }

  public Double getPrecio() { return precio; }
  public void setPrecio(Double precio) { this.precio = precio; }

  public Integer getStock() { return stock; }
  public void setStock(Integer stock) { this.stock = stock; }
}

