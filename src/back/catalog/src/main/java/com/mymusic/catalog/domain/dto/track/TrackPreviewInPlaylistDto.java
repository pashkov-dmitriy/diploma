package com.mymusic.catalog.domain.dto.track;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackPreviewInPlaylistDto extends MinimalTrackInfoDtoBase {
    String cover;

    public TrackPreviewInPlaylistDto(
            Long id,
            String name,
            Integer duration,
            String cover
    ) {
        super(id, name, duration, false);
        this.cover = cover;
    }

    @Override
    public Integer getDuration() {
        return duration;
    }
}
