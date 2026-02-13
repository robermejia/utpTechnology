package com.utp.technology.web.dto.pedido;

import java.util.List;

public class StorePedidoDto {

  private String metodoPago;
  private Double total;
  private List<StorePedidoDetalleDto> detalles;

  public StorePedidoDto() {
  }

  public StorePedidoDto(String metodoPago, Double total, List<StorePedidoDetalleDto> detalles) {
    this.metodoPago = metodoPago;
    this.total = total;
    this.detalles = detalles;
  }

  public String getMetodoPago() {
    return metodoPago;
  }

  public void setMetodoPago(String metodoPago) {
    this.metodoPago = metodoPago;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public List<StorePedidoDetalleDto> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<StorePedidoDetalleDto> detalles) {
    this.detalles = detalles;
  }
}
