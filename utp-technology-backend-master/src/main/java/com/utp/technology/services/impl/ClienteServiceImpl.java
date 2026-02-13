package com.utp.technology.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.utp.technology.web.dto.cliente.ListClienteDto;
import com.utp.technology.model.Cliente;
import com.utp.technology.repository.ClienteRepository;
import com.utp.technology.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

  private final ClienteRepository clienteRepository;

  public ClienteServiceImpl(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  @Override
  public Page<ListClienteDto> listCliente(String busqueda, Pageable pageReq) {
    try {
      List<Cliente> clientes = this.clienteRepository.findAll();

      List<ListClienteDto> filtered = clientes.stream()
          .filter(c -> busqueda == null ||
              c.getNombre().toLowerCase().contains(busqueda.toLowerCase()) ||
              c.getApellido().toLowerCase().contains(busqueda.toLowerCase()) ||
              c.getDni().contains(busqueda))
          .map(c -> {
            ListClienteDto dto = new ListClienteDto();
            dto.setId(c.getId());
            dto.setNombre(c.getNombre());
            dto.setApellido(c.getApellido());
            dto.setDni(c.getDni());
            dto.setTelefono(c.getTelefono());
            dto.setCorreo(c.getCorreo());
            return dto;
          })
          .collect(Collectors.toList());

      int start = (int) pageReq.getOffset();
      int end = Math.min((start + pageReq.getPageSize()), filtered.size());
      return new PageImpl<>(filtered.subList(start, end), pageReq, filtered.size());
    } catch (Exception e) {
      throw new RuntimeException("Error al listar clientes", e);
    }
  }

}
