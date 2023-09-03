package com.simbirsoft.fun_forms.service;

import lombok.AllArgsConstructor;
import com.simbirsoft.fun_forms.model.response.QuestionAnswer;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslatorService {
    private final RestClientCaller restClientCaller;

    public QuestionAnswer textToSmile(String text) {
        String englishText = restClientCaller.yandexTranslate(text, "en");
        String response = restClientCaller.engToEmoji(englishText);
        int spaceIndex = response.indexOf(" ");
        response = response.substring(0, spaceIndex);

        return new QuestionAnswer(response, text);
    }

    public QuestionAnswer songToSmile(String songName) {
        String englishText = restClientCaller.yandexTranslate(songName, "en");
        String response = restClientCaller.engToEmoji(englishText);
        int spaceIndex = response.indexOf(" ");
        response = response.substring(0, spaceIndex);

        return new QuestionAnswer(response, songName);
    }

    public QuestionAnswer doubleTranslate(String text){
        String koreanText = restClientCaller.yandexTranslate(text, "ko");
        String italianText = restClientCaller.yandexTranslate(koreanText, "it");
        String response = restClientCaller.yandexTranslate(italianText, "ru");

        return new QuestionAnswer(response, text);
    }
}
