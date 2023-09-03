package com.simbirsoft.fun_forms.service;

import com.amazonaws.services.s3.AmazonS3;
import com.simbirsoft.fun_forms.configuration.properties.S3Properties;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Properties s3Properties;
    private final AmazonS3 s3;

    @Value("${presigned.url.ttl}")
    private Long presignedUrlTtl;

    URL getPresignedUrl(String fileLink) {
        var expirationMillis = Instant.now().plusMillis(presignedUrlTtl);
        var expirationDate = Date.from(expirationMillis);

        return s3.generatePresignedUrl(s3Properties.getBucket(), fileLink, expirationDate);
    }

}
