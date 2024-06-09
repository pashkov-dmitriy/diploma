package com.mymusic.catalog.domain.dto.artist;

import lombok.Data;

@Data
public class ArtistWithRefDto {
    private String name;
    private String imageUrl;
    private String ref;
}
