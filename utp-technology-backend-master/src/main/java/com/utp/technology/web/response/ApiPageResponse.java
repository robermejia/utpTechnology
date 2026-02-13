package com.utp.technology.web.response;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

public class ApiPageResponse<T> {
  private boolean success;

  @Nullable
  private List<T> data;

  @Nullable
  private String message;

  private Integer currentPage;
  private Integer currentSize;

  private Long totalSize;
  private Integer totalPages;

  public ApiPageResponse() {}

  public ApiPageResponse(boolean success, Page<T> data, String message) {
    this.success = success;
    if (data != null) {
      this.data = data.getContent();
      this.currentPage = data.getNumber();
      this.currentSize = data.getNumberOfElements();
      this.totalPages = data.getTotalPages();
      this.totalSize = data.getTotalElements();
    }
    this.message = message;
  }

  public boolean isSuccess() { return success; }
  public void setSuccess(boolean success) { this.success = success; }

  @Nullable
  public List<T> getData() { return data; }
  public void setData(@Nullable List<T> data) { this.data = data; }

  @Nullable
  public String getMessage() { return message; }
  public void setMessage(@Nullable String message) { this.message = message; }

  public Integer getCurrentPage() { return currentPage; }
  public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }

  public Integer getCurrentSize() { return currentSize; }
  public void setCurrentSize(Integer currentSize) { this.currentSize = currentSize; }

  public Long getTotalSize() { return totalSize; }
  public void setTotalSize(Long totalSize) { this.totalSize = totalSize; }

  public Integer getTotalPages() { return totalPages; }
  public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }

  public static <T> ApiPageResponse<T> success(Page<T> data) {
    return new ApiPageResponse<T>(true, data, null);
  }

  public static <T> ApiPageResponse<T> badRequest(String message) {
    return new ApiPageResponse<T>(true, null, message);
  }
}

