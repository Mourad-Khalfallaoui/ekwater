package com.ekwateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestEkwateurApplication {

    public static void main(String[] args) {
        SpringApplication.from(EkwateurApplication::main).with(TestEkwateurApplication.class).run(args);
    }

}
