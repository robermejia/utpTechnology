package com.utp.technology.model;

import java.util.Date;
import java.util.List;

public class Pedido {

  private Integer id;
  private Integer idUsuario;
  private Integer idCliente;
  private Date fecha;
  private String estado;
  private Double total;
  private String metodoPago;
  private List<DetallePedido> detallesPedido;

  public Pedido() {
  }

  public Pedido(Integer id, Integer idUsuario, Integer idCliente, Date fecha, String estado, Double total,
      String metodoPago, List<DetallePedido> detallesPedido) {
    this.id = id;
    this.idUsuario = idUsuario;
    this.idCliente = idCliente;
    this.fecha = fecha;
    this.estado = estado;
    this.total = total;
    this.metodoPago = metodoPago;
    this.detallesPedido = detallesPedido;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public Integer getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Integer idCliente) {
    this.idCliente = idCliente;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
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

  public List<DetallePedido> getDetallesPedido() {
    return detallesPedido;
  }

  public void setDetallesPedido(List<DetallePedido> detallesPedido) {
    this.detallesPedido = detallesPedido;
  }
}
