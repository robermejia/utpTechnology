package com.utp.technology.web.dto.pedido;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "producto_id", "producto", "imagenProducto", "cantidad", "precio_unitario", "subtotal" })
public class ListDetallePedidoDto {

  private Integer id;
  private Integer producto_id;
  private String producto;
  private String imagenProducto;
  private Integer cantidad;
  private Double precio_unitario;
  private Double subtotal;

  public ListDetallePedidoDto() {
  }

  public ListDetallePedidoDto(Integer id, Integer producto_id, String producto, String imagenProducto, Integer cantidad,
      Double precio_unitario, Double subtotal) {
    this.id = id;
    this.producto_id = producto_id;
    this.producto = producto;
    this.imagenProducto = imagenProducto;
    this.cantidad = cantidad;
    this.precio_unitario = precio_unitario;
    this.subtotal = subtotal;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getProducto_id() {
    return producto_id;
  }

  public void setProducto_id(Integer producto_id) {
    this.producto_id = producto_id;
  }

  public String getProducto() {
    return producto;
  }

  public void setProducto(String producto) {
    this.producto = producto;
  }

  public String getImagenProducto() {
    return imagenProducto;
  }

  public void setImagenProducto(String imagenProducto) {
    this.imagenProducto = imagenProducto;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public Double getPrecio_unitario() {
    return precio_unitario;
  }

  public void setPrecio_unitario(Double precio_unitario) {
    this.precio_unitario = precio_unitario;
  }

  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }
}
