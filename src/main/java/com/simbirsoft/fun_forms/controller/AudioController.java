package com.simbirsoft.fun_forms.controller;

import com.simbirsoft.fun_forms.model.request.GetReversedAudioRequest;
import com.simbirsoft.fun_forms.model.request.GuessTheSongRequest;
import com.simbirsoft.fun_forms.model.request.ReverseAudioRequest;
import com.simbirsoft.fun_forms.model.response.GetReversedAudioResponse;
import com.simbirsoft.fun_forms.model.response.GuessTheSongResponse;
import com.simbirsoft.fun_forms.model.response.ReverseAudioResponse;
import com.simbirsoft.fun_forms.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ReverseAudioResponse reverseAudio(@RequestBody ReverseAudioRequest request) {
        return new ReverseAudioResponse(
                audioService.reverseAndGetOutput(request.url(), request.chunkDuration(), request.chunks())
                        .getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    GetReversedAudioResponse getReversedAudio(@RequestBody GetReversedAudioRequest request) {
        AudioService.PresignedReversedSongPart songPart =
                audioService.getPresignedReversedSongPart(request.songId(), request.index());
        return new GetReversedAudioResponse(
                request.songId(), request.index(), songPart.url());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/guess",produces = MediaType.APPLICATION_JSON_VALUE)
    GuessTheSongResponse guessTheSong(@RequestBody GuessTheSongRequest request) {
        return new GuessTheSongResponse(request.songId(),
                audioService.isTitleLikeIgnoreCase(request.songId(), request.guess()));
    }

}
