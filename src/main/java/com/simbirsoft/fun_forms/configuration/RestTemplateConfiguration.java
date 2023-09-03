package com.simbirsoft.fun_forms.configuration;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplateBuilder restTemplateBuilder(){
        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.setConnectTimeout(Duration.ofMillis(300000))
                .setReadTimeout(Duration.ofMillis(300000)).build();
    }
}
