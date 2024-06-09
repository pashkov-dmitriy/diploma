package com.mymusic.catalog.domain.dto.artist;

import com.mymusic.catalog.domain.dto.album.AlbumOfArtistPreviewDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArtistDto {
    private Long id;
    private String name;
    private String bio;
    private String imageUrl;
    private List<AlbumOfArtistPreviewDto> albums = new ArrayList<>();
}
