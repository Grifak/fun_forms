package com.simbirsoft.fun_forms.service;

import com.simbirsoft.fun_forms.model.response.EmojiResponse;
import com.simbirsoft.fun_forms.model.response.TranslatorResponse;
import com.simbirsoft.fun_forms.configuration.properties.TranslatorProperties;
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
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class RestClientCaller {
    private final RestTemplate restTemplate;
    private final TranslatorProperties translatorProperties;

    public String engToEmoji(String text){
        Map<String, String> requestBody = Map.of(
                "dropdown1", "English",
                "dropdown2", "✌🏻",
                "input1", text,
                "id","5kbXS1vMbHaaoIuKCg8983",
                "locale", "en-US");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<EmojiResponse> response = restTemplate.exchange(
                URI.create(translatorProperties.getEmojiTranslatorUrl()),
                HttpMethod.POST,
                requestEntity,
                EmojiResponse.class
                );

        return response.getBody().getResults().get(0);
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
