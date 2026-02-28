package com.utp.technology.web.dto.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "producto_id", "producto", "imagenProducto", "cantidad", "precio_unitario", "subtotal" })
public class ListDetallePedidoDto {

  private Integer id;
  @JsonProperty("producto_id")
  private Integer idProducto;
  private String producto;
  private String imagenProducto;
  private Integer cantidad;
  @JsonProperty("precio_unitario")
  private Double precioUnitario;
  private Double subtotal;

  public ListDetallePedidoDto() {
  }

  public ListDetallePedidoDto(Integer id, Integer idProducto, String producto, String imagenProducto, Integer cantidad,
      Double precioUnitario, Double subtotal) {
    this.id = id;
    this.idProducto = idProducto;
    this.producto = producto;
    this.imagenProducto = imagenProducto;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.subtotal = subtotal;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(Integer idProducto) {
    this.idProducto = idProducto;
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

  public Double getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }
}
