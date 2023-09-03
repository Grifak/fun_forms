package com.simbirsoft.fun_forms.controller;

import com.simbirsoft.fun_forms.service.TranslatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/translator")
public class TranslatorController {
    private final TranslatorService translatorService;

    @PostMapping("/text-smile")
    public ResponseEntity<String> textToSmile(@RequestParam String text){
        log.info("SMILE_TEXT >> text = {}", text);
        String response = translatorService.textToSmile(text);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/song-smile")
    public ResponseEntity<String> songToSmile(@RequestParam String songName){
        log.info("SMILE_SONG >> songName = {}", songName);
        String response = translatorService.songToSmile(songName);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/double-translate")
    public ResponseEntity<String> doubleTranslate(@RequestParam String text){
        log.info("DOUBLE_TRANSLATE >> text = {}", text);
        String response = translatorService.doubleTranslate(text);

        return ResponseEntity.ok()
                .body(response);
    }
}
