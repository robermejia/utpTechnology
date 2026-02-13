package com.utp.technology.web.dto.auth;

import com.utp.technology.web.dto.user.UserLoginDto;

public class LoginResponseDto {

  private String accessToken;
  private UserLoginDto user;

  public LoginResponseDto() {}

  public String getAccessToken() { return accessToken; }
  public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

  public UserLoginDto getUser() { return user; }
  public void setUser(UserLoginDto user) { this.user = user; }
}

