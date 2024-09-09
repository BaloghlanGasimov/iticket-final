package com.example.iticketfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class IticketFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(IticketFinalApplication.class, args);
    }

}
