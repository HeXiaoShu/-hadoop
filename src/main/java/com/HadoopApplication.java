package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Administrator
 */
@MapperScan("com.mapper")
@SpringBootApplication
public class HadoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopApplication.class, args);
    }

}
