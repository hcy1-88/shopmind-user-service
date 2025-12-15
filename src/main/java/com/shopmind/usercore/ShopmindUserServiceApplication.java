package com.shopmind.usercore;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.shopmind.usercore.mapper")
public class ShopmindUserServiceApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ShopmindUserServiceApplication.class, args);
    }
}
