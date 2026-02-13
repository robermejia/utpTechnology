package com.utp.technology.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.utp.technology.web.dto.cliente.ListClienteDto;

public interface ClienteService {

  public Page<ListClienteDto> listCliente(String busqueda, Pageable page);

}
