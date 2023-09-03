package com.simbirsoft.fun_forms.repository;

import com.simbirsoft.fun_forms.model.entity.Song;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findByIdAndTitleContainingIgnoreCase(Long id, String title);
}
