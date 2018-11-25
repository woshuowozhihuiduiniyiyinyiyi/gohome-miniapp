package com.hj.tj.gohome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hj.tj.gohome.mapper")
public class GoHomeMiniAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoHomeMiniAppApplication.class);
    }

}
