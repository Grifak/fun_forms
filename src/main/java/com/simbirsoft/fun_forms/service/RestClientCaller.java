package com.simbirsoft.fun_forms.service;

import com.simbirsoft.fun_forms.model.response.TranslatorResponse;
import com.simbirsoft.fun_forms.properties.TranslatorProperties;
import java.net.URI;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@Slf4j
public class RestClientCaller {
    private final RestTemplate restTemplate;
    private final TranslatorProperties translatorProperties;

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

        ResponseEntity<String> response = restTemplate.exchange(URI.create(translatorProperties.getEmojiTranslatorUrl()), HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }

    public String yandexTranslate(String text, String targetLanguageCode){
        Map<String, String> body = Map.of(
                "targetLanguageCode", targetLanguageCode,
                "texts", text,
                "folderId", translatorProperties.getCloudFolderId()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, translatorProperties.getCloudToken());

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<TranslatorResponse> response = restTemplate.exchange(
                URI.create(translatorProperties.getYandexTranslatorUrl()),
                HttpMethod.POST,
                requestEntity,
                TranslatorResponse.class
        );

        return response.getBody().getTranslations().get(0).getText();
    }
}
