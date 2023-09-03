package com.simbirsoft.fun_forms.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SmileService {
    private final RestClientCaller restClientCaller;

    public String textToSmile(String text) {
        return restClientCaller.translateToEmoji(text);
    }

    public String songToSmile(String songName) {
        return restClientCaller.translateToEmoji(songName);
    }
}
