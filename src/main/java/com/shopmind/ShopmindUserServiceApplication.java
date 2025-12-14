package com.shopmind;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.shopmind.mapper")
public class ShopmindUserServiceApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ShopmindUserServiceApplication.class, args);
    }
}
