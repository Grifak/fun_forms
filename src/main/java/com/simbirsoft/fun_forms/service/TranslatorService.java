package com.simbirsoft.fun_forms.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslatorService {
    private final RestClientCaller restClientCaller;

    public String textToSmile(String text) {
        String englishText = restClientCaller.yandexTranslate(text, "en");
        String response = restClientCaller.engToEmoji(englishText);
        int spaceIndex = response.indexOf(" ");

        return response.substring(0, spaceIndex);
    }

    public String songToSmile(String songName) {
        String englishText = restClientCaller.yandexTranslate(songName, "en");
        String response = restClientCaller.engToEmoji(englishText);
        int spaceIndex = response.indexOf(" ");

        return response.substring(0, spaceIndex);
    }

    public String doubleTranslate(String text){
        String koreanText = restClientCaller.yandexTranslate(text, "ko");
        String italianText = restClientCaller.yandexTranslate(koreanText, "it");
        return restClientCaller.yandexTranslate(italianText, "ru");
    }
}
