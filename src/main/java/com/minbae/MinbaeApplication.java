package com.minbae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
//@ServletComponentScan // fillter 어노테이션
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MinbaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinbaeApplication.class, args);
    }

}
