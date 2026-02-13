package com.utp.technology.services.impl;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.utp.technology.web.dto.product.ListProductoDto;
import com.utp.technology.web.dto.product.StoreProductDTO;
import com.utp.technology.model.Producto;
import com.utp.technology.repository.ProductoRepository;
import com.utp.technology.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;

  public ProductoServiceImpl(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }

  @Override
  public Page<ListProductoDto> listAll(String nombre, Pageable pageReq) {
    try {
      List<Producto> productos = this.productoRepository.findAll();

      List<ListProductoDto> filtered = productos.stream()
          .filter(p -> nombre == null || p.getNombre() == null
              || p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
          .map(p -> {
            ListProductoDto dto = new ListProductoDto();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setImagen(p.getImagen());
            dto.setPrecio(p.getPrecio() != null ? p.getPrecio() : 0.0);
            dto.setStock(p.getStock() != null ? p.getStock() : 0);
            return dto;
          })
          .collect(Collectors.toList());

      int start = (int) pageReq.getOffset();
      int end = Math.min((start + pageReq.getPageSize()), filtered.size());

      if (start > filtered.size()) {
        return new PageImpl<>(List.of(), pageReq, filtered.size());
      }

      return new PageImpl<>(filtered.subList(start, end), pageReq, filtered.size());
    } catch (Exception e) {
      throw new RuntimeException("Error al listar productos", e);
    }
  }

  @Override
  public Optional<Producto> findById(Integer id) {
    try {
      return this.productoRepository.findById(id);
    } catch (Exception e) {
      throw new RuntimeException("Error al buscar producto por id", e);
    }
  }

  @Override
  public Producto guardar(StoreProductDTO productoData) {
    try {
      Producto p = new Producto();
      p.setNombre(productoData.getNombre());
      p.setImagen(productoData.getImagen());
      p.setPrecio(productoData.getPrecio());
      p.setStock(productoData.getStock());
      p.setDescripcion(productoData.getDescripcion());
      p.setCategoria(productoData.getCategoria());
      return this.productoRepository.save(p);
    } catch (Exception e) {
      throw new RuntimeException("Error al guardar producto", e);
    }
  }

  @Override
  public Producto editar(Producto producto) {
    try {
      return this.productoRepository.save(producto);
    } catch (Exception e) {
      throw new RuntimeException("Error al editar producto", e);
    }
  }

  @Override
  public void eliminar(Producto producto) {
    try {
      this.productoRepository.delete(producto);
    } catch (Exception e) {
      throw new RuntimeException("Error al eliminar producto", e);
    }
  }

}
