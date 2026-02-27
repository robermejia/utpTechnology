package com.utp.technology.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.utp.technology.model.Usuario;
import com.utp.technology.model.Rol;
import com.utp.technology.repository.UsuarioRepository;
import com.utp.technology.repository.RolRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;
  private final RolRepository rolRepository;

  public CustomUserDetailsService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
    this.usuarioRepository = usuarioRepository;
    this.rolRepository = rolRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
    try {
      Optional<Usuario> usuarioOpt = this.usuarioRepository.findByCorreo(correo);

      if (usuarioOpt.isEmpty()) {
        throw new UsernameNotFoundException("Usuario no encontrado: " + correo);
      }

      Usuario usuario = usuarioOpt.get();

      String roleName = "USER";
      if (usuario.getId_rol() != null) {
        Rol rol = rolRepository.findById(usuario.getId_rol()).orElse(null);
        if (rol != null) {
          roleName = rol.getNombreRol();
        }
      }

      return User.builder()
          .username(usuario.getCorreo())
          .password(usuario.getClave())
          .roles(roleName)
          .build();
    } catch (UsernameNotFoundException e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException("Error al cargar detalles de usuario", e);
    }
  }
}
