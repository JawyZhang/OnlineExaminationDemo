package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.boot.mapper")
@ServletComponentScan
public class OnlineExaminationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineExaminationDemoApplication.class, args);
    }

}
