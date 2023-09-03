package com.simbirsoft.fun_forms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan("com.simbirsoft.fun_forms.configuration.properties")
public class FunFormsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunFormsApplication.class, args);
    }

}
