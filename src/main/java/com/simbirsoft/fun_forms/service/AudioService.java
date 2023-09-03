package com.simbirsoft.fun_forms.service;

import com.simbirsoft.fun_forms.client.YoutubeReverseClient;
import com.simbirsoft.fun_forms.mapper.SongMapper;
import com.simbirsoft.fun_forms.model.entity.ReversedSongPart;
import com.simbirsoft.fun_forms.model.entity.Song;
import com.simbirsoft.fun_forms.repository.ReversedSongPartRepository;
import com.simbirsoft.fun_forms.repository.SongRepository;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AudioService {

    private final YoutubeReverseClient youtubeReverseClient;
    private final SongMapper songMapper;
    private final SongRepository songRepository;
    private final ReversedSongPartRepository reversedSongPartRepository;
    private final S3Service s3Service;

    public Song reverseAndGetOutput(String youtubeLink, Integer chunkDuration, Integer chunks) {

        var response = youtubeReverseClient.convertAndReverse(
                new YoutubeReverseClient.ConvertAndReverseRequest(youtubeLink, chunkDuration, chunks));

        var song = Song.builder()
                .title(response.videoTitle())
                .originalUrl(youtubeLink)
                .chunkDuration(chunkDuration)
                .chunks(chunks)
                .build();
        Song newSong = songRepository.saveAndFlush(song);

        List<ReversedSongPart> reversedSongParts =
                songMapper.songPartDtoToModel(newSong, response.reversedSongPartDtos());

        reversedSongPartRepository.saveAll(reversedSongParts);
        return song;
    }

    public PresignedReversedSongPart getPresignedReversedSongPart(Long songId, Integer index) {
        Optional<Song> optionalSong = songRepository.findById(songId);
        var reversedSongParts = optionalSong.map(Song::getReversedSongParts).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Песня с идентификатором %s не найдена", songId)));
        ReversedSongPart reversedSongPart = reversedSongParts.get(Math.toIntExact(index));

        return new PresignedReversedSongPart(index,
                s3Service.getPresignedUrl(reversedSongPart.getUrl()));
    }

    public record PresignedReversedSongPart(Integer index, URL url) {
    }
}
