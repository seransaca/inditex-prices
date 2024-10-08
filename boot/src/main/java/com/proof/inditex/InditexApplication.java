package com.proof.inditex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.proof.inditex"})
public class InditexApplication {

  public static void main(String[] args) {
    SpringApplication.run(InditexApplication.class, args);
  }

}
