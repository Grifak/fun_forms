package com.simbirsoft.fun_forms.model.response;

import lombok.Data;
import java.util.List;

@Data
public class TranslatorResponse {
    private List<Translation> translations;

    @Data
    public static class Translation {
        private String text;
        private String detectedLanguageCode;
    }
}
