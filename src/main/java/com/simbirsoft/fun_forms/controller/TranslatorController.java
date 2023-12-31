package com.simbirsoft.fun_forms.controller;

import com.simbirsoft.fun_forms.model.response.QuestionAnswer;
import com.simbirsoft.fun_forms.service.TranslatorService;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Перевод текста в смайлики")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Исходный текст и ответ в виде смайликов"
            )
    }
    )
    public ResponseEntity<QuestionAnswer> textToSmile(@RequestParam String text){
        log.info("SMILE_TEXT >> text = {}", text);
        QuestionAnswer response = translatorService.textToSmile(text);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/song-smile")
    @Operation(summary = "Перевод названия песни в смайлики")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Названия песни и ответ в виде смайликов"
            )
    }
    )
    public ResponseEntity<QuestionAnswer> songToSmile(@RequestParam String songName){
        log.info("SMILE_SONG >> songName = {}", songName);
        QuestionAnswer response = translatorService.songToSmile(songName);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/double-translate")
    @Operation(summary = "Двойной перевод текста песни")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Исходный код песни и переведенный"
            )
    }
    )
    public ResponseEntity<QuestionAnswer> doubleTranslate(@RequestParam String text){
        log.info("DOUBLE_TRANSLATE >> text = {}", text);
        QuestionAnswer response = translatorService.doubleTranslate(text);

        return ResponseEntity.ok()
                .body(response);
    }
}
