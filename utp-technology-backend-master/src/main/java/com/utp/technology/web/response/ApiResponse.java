package com.utp.technology.web.response;

import org.springframework.lang.Nullable;

public class ApiResponse<T> {

  private boolean success;

  @Nullable
  private T data;

  @Nullable
  private String message;

  public ApiResponse() {}

  public ApiResponse(boolean success, @Nullable T data, @Nullable String message) {
    this.success = success;
    this.data = data;
    this.message = message;
  }

  public boolean isSuccess() { return success; }
  public void setSuccess(boolean success) { this.success = success; }

  @Nullable
  public T getData() { return data; }
  public void setData(@Nullable T data) { this.data = data; }

  @Nullable
  public String getMessage() { return message; }
  public void setMessage(@Nullable String message) { this.message = message; }

  public static <T> ApiResponse<T> notFound() {
    return new ApiResponse<T>(false, null, "No se encontró el recurso");
  }

  public static <T> ApiResponse<T> notFound(String message) {
    return new ApiResponse<T>(false, null, message);
  }

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<T>(true, data, null);
  }

  public static <T> ApiResponse<T> success(T data, String message) {
    return new ApiResponse<T>(true, data, message);
  }

  public static <T> ApiResponse<T> badRequest(T data, String message) {
    return new ApiResponse<T>(false, data, message);
  }

}

