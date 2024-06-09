package com.mymusic.catalog.domain.dto.album;

import lombok.Data;

@Data
public class AlbumOfArtistPreviewDto {
    private Long id;
    private String name;
    private String genre;
    private String date;
    private String coverUrl;
}
