package com.utp.technology.services;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.utp.technology.web.dto.product.ListProductoDto;
import com.utp.technology.web.dto.product.StoreProductDTO;
import com.utp.technology.model.Producto;

public interface ProductoService {

  public Page<ListProductoDto> listAll(String nombre, Pageable pageReq);

  public Optional<Producto> findById(Integer id);

  public Producto guardar(StoreProductDTO productoData);

  public Producto editar(Producto producto);

  public void eliminar(Producto producto);

}
