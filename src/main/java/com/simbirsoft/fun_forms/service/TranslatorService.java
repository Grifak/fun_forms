package com.simbirsoft.fun_forms.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslatorService {
    private final RestClientCaller restClientCaller;

    public String textToSmile(String text) {
        return restClientCaller.translateToEmoji(text);
    }

    public String songToSmile(String songName) {
        return restClientCaller.translateToEmoji(songName);
    }

    public String doubleTranslate(String text){
        String koreanText = restClientCaller.yandexTranslate(text, "ko");
        String italianText = restClientCaller.yandexTranslate(koreanText, "it");
        return restClientCaller.yandexTranslate(italianText, "ru");
    }
}
