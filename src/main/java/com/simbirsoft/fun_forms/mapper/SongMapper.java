package com.simbirsoft.fun_forms.mapper;

import com.simbirsoft.fun_forms.client.YoutubeReverseClient;
import com.simbirsoft.fun_forms.model.entity.ReversedSongPart;
import com.simbirsoft.fun_forms.model.entity.Song;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface SongMapper {

    /**
     * Преобразовывает {@link YoutubeReverseClient.ReversedSongPartDto}
     * в {@link ReversedSongPart}.
     *
     * @param reversedSongPartDto {@link YoutubeReverseClient.ReversedSongPartDto}
     * @return {@link ReversedSongPart}
     */
    default ReversedSongPart songPartDtoToModel(Song song,
                                                YoutubeReverseClient.ReversedSongPartDto reversedSongPartDto) {
        ReversedSongPart reversedSongPart = new ReversedSongPart();
        reversedSongPart.setSong(song);
        reversedSongPart.setUrl(reversedSongPartDto.url());
        return reversedSongPart;
    }

    /**
     * Преобразовывает список {@link YoutubeReverseClient.ReversedSongPartDto}
     * в список {@link ReversedSongPart}.
     *
     * @param reversedSongPartDtos список {@link YoutubeReverseClient.ReversedSongPartDto}
     * @return список {@link ReversedSongPart}
     */
    default List<ReversedSongPart> songPartDtoToModel(Song song,
                                                      List<YoutubeReverseClient.ReversedSongPartDto> reversedSongPartDtos) {
        List<ReversedSongPart> reversedSongParts = new ArrayList<>();

        for (YoutubeReverseClient.ReversedSongPartDto dto : reversedSongPartDtos) {
            ReversedSongPart reversedSongPart = new ReversedSongPart();
            reversedSongPart.setSong(song);
            reversedSongPart.setIndex(dto.index());
            reversedSongPart.setUrl(dto.url());
            reversedSongParts.add(reversedSongPart);
        }

        return reversedSongParts;
    }

    /**
     * Преобразовывает список {@link ReversedSongPart}
     * в список {@link YoutubeReverseClient.ReversedSongPartDto}.
     *
     * @param reversedSongParts список {@link ReversedSongPart}
     * @return список {@link YoutubeReverseClient.ReversedSongPartDto}
     */
    List<YoutubeReverseClient.ReversedSongPartDto> songPartModelToDto(List<ReversedSongPart>
                                                                              reversedSongParts);


    /**
     * Преобразовывает {@link ReversedSongPart}
     * в {@link YoutubeReverseClient.ReversedSongPartDto}.
     *
     * @param reversedSongPart {@link ReversedSongPart}
     * @return {@link YoutubeReverseClient.ReversedSongPartDto}
     */
    YoutubeReverseClient.ReversedSongPartDto songPartDtoToModel(ReversedSongPart reversedSongPart);


}
