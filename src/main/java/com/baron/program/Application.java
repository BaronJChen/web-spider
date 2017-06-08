package com.baron.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Jason on 2017/5/31.
 */
@SpringBootApplication
@ComponentScan("com.baron")
public class Application {
    public static void main(String [] args) {
        SpringApplication.run(Application.class);
    }
}