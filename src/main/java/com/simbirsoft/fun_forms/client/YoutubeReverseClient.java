package com.simbirsoft.fun_forms.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "${client.youtube-reverse-service.name}",
        url = "${client.youtube-reverse-service.url}"
)
@Service
public interface YoutubeReverseClient {

    @PostMapping(value = "/process",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ConvertAndReverseResponse convertAndReverse(@RequestBody ConvertAndReverseRequest request);

    record ConvertAndReverseRequest(String link, Integer chunkDuration, Integer chunks) {
    }

    record ConvertAndReverseResponse(String videoTitle, List<ReversedSongPartDto> reversedSongPartDtos) {
    }

    record ReversedSongPartDto(Integer index, String url) {
    }
}
