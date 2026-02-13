package com.utp.technology.web.dto.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StorePedidoDetalleDto {

  @JsonProperty("id_producto")
  private Integer idProducto;

  private Integer cantidad;

  private Double precioUnitario;

  private Double subtotal;

  public StorePedidoDetalleDto() {
  }

  public StorePedidoDetalleDto(Integer idProducto, Integer cantidad, Double precioUnitario, Double subtotal) {
    this.idProducto = idProducto;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.subtotal = subtotal;
  }

  public Integer getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(Integer idProducto) {
    this.idProducto = idProducto;
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
