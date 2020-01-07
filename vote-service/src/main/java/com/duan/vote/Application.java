package com.duan.vote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
@SpringBootApplication
@MapperScan("com.duan.vote.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
