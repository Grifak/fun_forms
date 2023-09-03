package com.simbirsoft.fun_forms.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3")
@Getter
@Setter
public final class S3Properties {
    /**
     * Access-ключ.
     */
    private String accessKey;
    /**
     * Secret-ключ.
     */
    private String secretKey;
    /**
     * Бакет хранилища.
     */
    private String bucket;
    /**
     * Регион файлового хранилища.
     */
    private String region;

    /**
     * Адрес файлового хранилища.
     */
    private String endpoint;
}