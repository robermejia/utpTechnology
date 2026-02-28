package com.utp.technology.services;

import java.util.List;
import java.util.Optional;

import com.utp.technology.web.dto.auth.SignUpRequestDTO;
import com.utp.technology.web.dto.user.UserListDto;
import com.utp.technology.model.Usuario;

public interface UsuarioService {

  public List<UserListDto> listAll();

  public Optional<Usuario> findById(Integer id);

  public Optional<Usuario> findByCorreo(String correo);

  public Usuario registrarUsuario(SignUpRequestDTO data);

  public boolean checkPassword(String hashedPassword, String password);

  public Usuario guardar(Usuario usuario);

  public Usuario editar(Integer id, Usuario usuario);

  public void eliminar(Integer id);

  public int cleanAnomalies();

}
