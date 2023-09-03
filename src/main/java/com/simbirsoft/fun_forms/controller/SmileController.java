package com.simbirsoft.fun_forms.controller;

import lombok.AllArgsConstructor;
import com.simbirsoft.fun_forms.service.SmileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/smile")
public class SmileController {
    private final SmileService smileService;

    @PostMapping("/text")
    public ResponseEntity<String> textToSmile(@RequestParam String text){
        log.info("SMILE_TEXT >> text = {}", text);
        String response = smileService.textToSmile(text);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/song")
    public ResponseEntity<String> songToSmile(@RequestParam String songName){
        log.info("SMILE_SONG >> songName = {}", songName);
        String response = smileService.songToSmile(songName);

        return ResponseEntity.ok()
                .body(response);
    }
}
