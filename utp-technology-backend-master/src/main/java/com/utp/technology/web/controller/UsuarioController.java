package com.utp.technology.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.technology.web.dto.user.UserListDto;
import com.utp.technology.web.response.ApiResponse;
import com.utp.technology.model.Usuario;
import com.utp.technology.security.AuthService;
import com.utp.technology.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;
  private final AuthService authService;

  public UsuarioController(UsuarioService usuarioService, AuthService authService) {
    this.usuarioService = usuarioService;
    this.authService = authService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<UserListDto>>> index() {
    if (!this.authService.getCurrentUser().getRolId().equals(1))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.badRequest(null, "No Autorizado"));
    return ResponseEntity.ok().body(ApiResponse.success(this.usuarioService.listAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Usuario>> show(@PathVariable() Integer id) {
    if (!this.authService.getCurrentUser().getRolId().equals(1))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.badRequest(null, "No Autorizado"));
    Optional<Usuario> usuario = this.usuarioService.findById(id);

    if (usuario.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.notFound());
    }

    return ResponseEntity.ok().body(ApiResponse.success(usuario.get()));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Usuario>> store(@RequestBody Usuario usuario) {
    if (!this.authService.getCurrentUser().getRolId().equals(1))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.badRequest(null, "No Autorizado"));
    return ResponseEntity.ok()
        .body(ApiResponse.success(this.usuarioService.guardar(usuario), "Usuario creado exitosamente"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<Usuario>> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
    if (!this.authService.getCurrentUser().getRolId().equals(1))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.badRequest(null, "No Autorizado"));
    return ResponseEntity.ok()
        .body(ApiResponse.success(this.usuarioService.editar(id, usuario), "Usuario actualizado exitosamente"));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    if (!this.authService.getCurrentUser().getRolId().equals(1))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.badRequest(null, "No Autorizado"));

    if (this.authService.getCurrentUser().getId().equals(id))
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(ApiResponse.badRequest(null, "No puedes eliminar tu propia cuenta de Administrador."));

    this.usuarioService.eliminar(id);
    return ResponseEntity.ok().body(ApiResponse.success(null, "Usuario eliminado"));
  }

}
