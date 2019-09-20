package br.com.ryoshino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApplicationTransaction {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTransaction.class, args);
    }
}
