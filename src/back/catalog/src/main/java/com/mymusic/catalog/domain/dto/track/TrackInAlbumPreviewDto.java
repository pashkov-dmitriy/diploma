package com.mymusic.catalog.domain.dto.track;

import com.mymusic.catalog.domain.interfaces.Durable;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class TrackInAlbumPreviewDto extends MinimalTrackInfoDtoBase implements Durable {
    private Integer position;

    public TrackInAlbumPreviewDto(
            Long id,
            String name,
            Integer duration,
            Integer position
    ) {
        super(id, name, duration, false);
        this.position = position;
    }

    @Override
    public Integer getDuration() {
        return duration;
    }
}
