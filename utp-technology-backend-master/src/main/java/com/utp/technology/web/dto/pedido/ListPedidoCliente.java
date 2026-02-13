package com.utp.technology.web.dto.pedido;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "fecha", "estado", "total", "metodoPago" })
public class ListPedidoCliente {

  private Integer id;
  private String fecha;
  private String estado;
  private Double total;
  private String metodoPago;

  public ListPedidoCliente() {
  }

  public ListPedidoCliente(Integer id, String fecha, String estado, Double total, String metodoPago) {
    this.id = id;
    this.fecha = fecha;
    this.estado = estado;
    this.total = total;
    this.metodoPago = metodoPago;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public String getMetodoPago() {
    return metodoPago;
  }

  public void setMetodoPago(String metodoPago) {
    this.metodoPago = metodoPago;
  }
}
