package com.hk.wwz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.hk.wwz.dao")
@EnableTransactionManagement
public class HkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HkApplication.class, args);
    }

}
