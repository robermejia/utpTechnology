package com.utp.technology.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.utp.technology.web.dto.pedido.ListDetallePedidoDto;
import com.utp.technology.web.dto.pedido.ListPedidoAdmin;
import com.utp.technology.web.dto.pedido.ListPedidoCliente;
import com.utp.technology.web.dto.pedido.StorePedidoDto;
import com.utp.technology.model.Comprobante;
import com.utp.technology.model.DetallePedido;
import com.utp.technology.model.Pedido;
import com.utp.technology.model.Producto;
import com.utp.technology.repository.ClienteRepository;
import com.utp.technology.repository.ComprobanteRepository;
import com.utp.technology.repository.DetallePedidoRepository;
import com.utp.technology.repository.PedidoRepository;
import com.utp.technology.repository.ProductoRepository;
import com.utp.technology.security.JwtUsuario;
import com.utp.technology.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

  private final ClienteRepository clienteRepository;
  private final ComprobanteRepository comprobanteRepository;
  private final PedidoRepository pedidoRepository;
  private final ProductoRepository productoRepository;
  private final DetallePedidoRepository detallePedidoRepository;

  public PedidoServiceImpl(ClienteRepository clienteRepository, ComprobanteRepository comprobanteRepository,
      PedidoRepository pedidoRepository, ProductoRepository productoRepository,
      DetallePedidoRepository detallePedidoRepository) {
    this.clienteRepository = clienteRepository;
    this.comprobanteRepository = comprobanteRepository;
    this.pedidoRepository = pedidoRepository;
    this.productoRepository = productoRepository;
    this.detallePedidoRepository = detallePedidoRepository;
  }

  @Override
  public Optional<Pedido> findById(Integer id) {
    try {
      return this.pedidoRepository.findById(id);
    } catch (Exception e) {
      throw new RuntimeException("Error al buscar pedido por id", e);
    }
  }

  @Override
  public Page<ListPedidoAdmin> findAllAdmin(PageRequest pageReq) {
    try {
      List<ListPedidoAdmin> list = this.pedidoRepository.findAll().stream()
          .map(p -> {
            ListPedidoAdmin dto = new ListPedidoAdmin();
            dto.setId(p.getId());
            dto.setFecha(p.getFecha() != null ? new SimpleDateFormat("dd/MM/yyyy").format(p.getFecha()) : "N/A");
            dto.setEstado(p.getEstado());
            dto.setTotal(p.getTotal());
            dto.setMetodoPago(p.getMetodoPago());

            try {
              var clienteOpt = this.clienteRepository.findByUsuarioId(p.getIdUsuario());
              dto.setUsuario(
                  clienteOpt.isPresent() ? clienteOpt.get().getNombre() + " " + clienteOpt.get().getApellido() : "N/A");
            } catch (Exception ex) {
              dto.setUsuario("Error");
            }

            return dto;
          })
          .collect(Collectors.toList());

      int start = (int) pageReq.getOffset();
      int end = Math.min((start + pageReq.getPageSize()), list.size());
      return new PageImpl<>(list.subList(start, end), pageReq, list.size());
    } catch (Exception e) {
      throw new RuntimeException("Error al listar pedidos para admin", e);
    }
  }

  @Override
  public Page<ListPedidoCliente> findByUsuarioId(Integer usuarioId, PageRequest pageReq) {
    try {
      List<ListPedidoCliente> list = this.pedidoRepository.findByUsuarioId(usuarioId).stream()
          .map(p -> {
            ListPedidoCliente dto = new ListPedidoCliente();
            dto.setId(p.getId());
            dto.setFecha(p.getFecha() != null ? new SimpleDateFormat("dd/MM/yyyy").format(p.getFecha()) : "N/A");
            dto.setEstado(p.getEstado());
            dto.setTotal(p.getTotal());
            dto.setMetodoPago(p.getMetodoPago());
            return dto;
          })
          .collect(Collectors.toList());

      int start = (int) pageReq.getOffset();
      int end = Math.min((start + pageReq.getPageSize()), list.size());
      return new PageImpl<>(list.subList(start, end), pageReq, list.size());
    } catch (Exception e) {
      throw new RuntimeException("Error al listar pedidos por usuario id", e);
    }
  }

  @Override
  public void guardarPedido(StorePedidoDto pedidoData, JwtUsuario jwtUsuario) {
    try {
      Pedido pedido = new Pedido();
      pedido.setIdUsuario(jwtUsuario.getId());
      pedido.setFecha(new Date());
      pedido.setEstado("PENDIENTE");
      pedido.setMetodoPago(pedidoData.getMetodoPago());
      pedido.setTotal(pedidoData.getTotal());

      pedido = this.pedidoRepository.save(pedido);

      if (pedidoData.getDetalles() != null) {
        for (var d : pedidoData.getDetalles()) {
          DetallePedido detalle = new DetallePedido();
          detalle.setIdPedido(pedido.getId());
          detalle.setIdProducto(d.getIdProducto());
          detalle.setCantidad(d.getCantidad());
          detalle.setPrecioUnitario(d.getPrecioUnitario());
          detalle.setSubtotal(d.getSubtotal());
          this.detallePedidoRepository.save(detalle);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Error al guardar pedido", e);
    }
  }

  @Override
  public List<ListDetallePedidoDto> listPedidoDetalles(Integer pedidoId) {
    List<DetallePedido> detalles = this.detallePedidoRepository.findByIdPedido(pedidoId);
    return detalles.stream().map(d -> {
      ListDetallePedidoDto dto = new ListDetallePedidoDto();
      dto.setIdProducto(d.getIdProducto());
      dto.setCantidad(d.getCantidad());
      dto.setPrecioUnitario(d.getPrecioUnitario());
      dto.setSubtotal(d.getSubtotal());

      try {
        var prodOpt = this.productoRepository.findById(d.getIdProducto());
        dto.setProducto(prodOpt.isPresent() ? prodOpt.get().getNombre() : "Producto Desconocido");
      } catch (Exception e) {
        dto.setProducto("Desconocido");
      }
      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  public void actualizar(Pedido pedido) {
    try {
      this.pedidoRepository.save(pedido);
    } catch (Exception e) {
      throw new RuntimeException("Error al actualizar pedido", e);
    }
  }

  @Override
  public void pagar(Pedido pedido) {
    try {
      pedido.setEstado("PAGADO");
      this.pedidoRepository.save(pedido);

      Comprobante comprobante = new Comprobante();
      comprobante.setIdPedido(pedido.getId());
      comprobante.setFechaEmision(new Date());
      comprobante.setTotal(pedido.getTotal());
      this.comprobanteRepository.save(comprobante);
    } catch (Exception e) {
      throw new RuntimeException("Error al pagar pedido", e);
    }
  }

}
