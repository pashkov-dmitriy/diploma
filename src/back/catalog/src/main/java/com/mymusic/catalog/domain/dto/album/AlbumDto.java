package com.mymusic.catalog.domain.dto.album;

import com.mymusic.catalog.domain.dto.track.TrackInAlbumPreviewDto;
import com.mymusic.catalog.domain.dto.artist.ArtistPreviewDto;
import lombok.Data;

import java.util.List;

@Data
public class AlbumDto {
    private Long id;
    private ArtistPreviewDto owner;
    private String name;
    private String date;
    private String genre;
    private String description;
    private String coverUrl;
    private List<TrackInAlbumPreviewDto> tracks;
}
