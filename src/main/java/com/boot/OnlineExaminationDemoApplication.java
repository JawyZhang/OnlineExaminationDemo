package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.boot.mapper")
@ServletComponentScan
@EnableScheduling
public class OnlineExaminationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineExaminationDemoApplication.class, args);
    }

}
