package com.duan.vote;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDubbo(scanBasePackages = "com.duan.vote")
@PropertySource("classpath:/dubbo-consumer.properties")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
