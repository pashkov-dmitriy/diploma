package com.mymusic.catalog.domain.dto.track;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackInfoDto {
    private Long id;
    private String name;
    private String artist;
    private String coverUrl;
}
