package com.simbirsoft.fun_forms.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "translator")
public class TranslatorProperties {
    private String emojiTranslatorUrl;
    private String cloudToken;
    private String cloudFolderId;
    private String yandexTranslatorUrl;
}
