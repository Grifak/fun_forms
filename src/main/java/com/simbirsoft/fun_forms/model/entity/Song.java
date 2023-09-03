package com.simbirsoft.fun_forms.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "song")
public class Song {

    /**
     * ID песни.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Название оригинального видео.
     */
    @Column(name = "title")
    private String title;

    /**
     * Общее количество отрезков в песне.
     */
    @Column(name = "chunks")
    private Integer chunks;

    /**
     * Длина одного отрезка песни.
     */
    @Column(name = "chunk_duration")
    private Integer chunkDuration;

    /**
     * Ссылка на оригинальное видео.
     */
    @Column(name = "original_url")
    private String originalUrl;

    @OneToMany(mappedBy = "song", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReversedSongPart> reversedSongParts = new ArrayList<>();
}