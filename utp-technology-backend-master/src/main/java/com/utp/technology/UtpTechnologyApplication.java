package com.utp.technology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class UtpTechnologyApplication {

  public static void main(String[] args) {
    SpringApplication.run(UtpTechnologyApplication.class, args);
  }

}
