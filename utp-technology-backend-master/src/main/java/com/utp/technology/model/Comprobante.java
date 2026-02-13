package com.utp.technology.model;

import java.util.Date;

public class Comprobante {

    private Integer id;
    private Integer idPedido;
    private String tipoComprobante;
    private String serie;
    private String numero;
    private Date fechaEmision;
    private Double total;

    public Comprobante() {}

    public Comprobante(Integer id, Integer idPedido, String tipoComprobante, String serie, String numero, Date fechaEmision, Double total) {
        this.id = id;
        this.idPedido = idPedido;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public String getTipoComprobante() { return tipoComprobante; }
    public void setTipoComprobante(String tipoComprobante) { this.tipoComprobante = tipoComprobante; }

    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Date getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Date fechaEmision) { this.fechaEmision = fechaEmision; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}

