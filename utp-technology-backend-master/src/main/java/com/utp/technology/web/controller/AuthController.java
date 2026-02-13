package com.utp.technology.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.technology.web.dto.auth.LoginRequestDTO;
import com.utp.technology.web.dto.auth.LoginResponseDto;
import com.utp.technology.web.dto.auth.SignUpRequestDTO;
import com.utp.technology.web.dto.user.UserLoginDto;
import com.utp.technology.web.response.ApiResponse;
import com.utp.technology.model.Usuario;
import com.utp.technology.security.JWTService;
import com.utp.technology.services.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
@SecurityRequirements()
public class AuthController {

  private final JWTService jwtService;
  private final UsuarioService usuarioService;

  public AuthController(JWTService jwtService, UsuarioService usuarioService) {
    this.jwtService = jwtService;
    this.usuarioService = usuarioService;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<ApiResponse<LoginResponseDto>> signUp(@Valid @RequestBody SignUpRequestDTO request,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      Map<String, String> errors = new HashMap<>();
      for (FieldError error : bindingResult.getFieldErrors()) {
        errors.put(error.getField(), error.getDefaultMessage());
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.badRequest(null,
          errors.toString()));
    }

    Optional<Usuario> usuario = this.usuarioService.findByCorreo(request.getEmail());

    if (usuario.isPresent()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.notFound("Correo ya registrado"));
    }

    try {
      Usuario nuevoUsuario = this.usuarioService.registrarUsuario(request);
      String token = this.jwtService.generateToken(nuevoUsuario);
      LoginResponseDto res = new LoginResponseDto();
      res.setAccessToken(token);
      res.setUser(new UserLoginDto(nuevoUsuario));
      return ResponseEntity.ok().body(ApiResponse.success(res));
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.notFound(ex.getMessage()));
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.notFound(ex.getMessage()));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDTO request,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      Map<String, String> errors = new HashMap<>();
      for (FieldError error : bindingResult.getFieldErrors()) {
        errors.put(error.getField(), error.getDefaultMessage());
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.badRequest(null,
          errors.toString()));
    }

    Optional<Usuario> usuario = this.usuarioService.findByCorreo(request.getEmail());

    if (usuario.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.notFound("Credenciales incorrectas"));
    }

    if (!this.usuarioService.checkPassword(usuario.get().getClave(), request.getPassword())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.notFound("Credenciales incorrectas"));
    }

    String token = this.jwtService.generateToken(usuario.get());

    LoginResponseDto res = new LoginResponseDto();
    res.setAccessToken(token);
    res.setUser(new UserLoginDto(usuario.get()));
    return ResponseEntity.ok().body(ApiResponse.success(res, "Login Exitoso"));
  }

}
