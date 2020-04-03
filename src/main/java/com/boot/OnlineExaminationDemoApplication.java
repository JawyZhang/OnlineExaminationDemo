package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boot.mapper")
public class OnlineExaminationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineExaminationDemoApplication.class, args);
    }

}
