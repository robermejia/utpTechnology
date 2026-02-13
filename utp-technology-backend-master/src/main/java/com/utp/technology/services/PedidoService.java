package com.utp.technology.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.utp.technology.web.dto.pedido.ListDetallePedidoDto;
import com.utp.technology.web.dto.pedido.ListPedidoAdmin;
import com.utp.technology.web.dto.pedido.ListPedidoCliente;
import com.utp.technology.web.dto.pedido.StorePedidoDto;
import com.utp.technology.model.Pedido;
import com.utp.technology.security.JwtUsuario;

public interface PedidoService {

  public Optional<Pedido> findById(Integer id);

  public Page<ListPedidoAdmin> findAllAdmin(PageRequest pageReq);

  public Page<ListPedidoCliente> findByUsuarioId(Integer usuarioId, PageRequest pageReq);

  public void guardarPedido(StorePedidoDto pedidoData, JwtUsuario jwtUsuario);

  public List<ListDetallePedidoDto> listPedidoDetalles(Integer pedidoId);

  public void actualizar(Pedido pedido);

  public void pagar(Pedido pedido);

}
