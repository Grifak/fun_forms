package com.simbirsoft.fun_forms.service;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientCaller {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${translator.url}")
    private String translatorUrl;

    public String translateToEmoji(String text){
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("f", "ru");
        requestBody.add("t", "emoji");
        requestBody.add("o", " ft00-");
        requestBody.add("d", text);
        requestBody.add("k", "giwlmvwwxpp");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(translatorUrl), HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }
}
