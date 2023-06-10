package com.example.seam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.seam.mapper")
public class SeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeamApplication.class, args);
    }

}
