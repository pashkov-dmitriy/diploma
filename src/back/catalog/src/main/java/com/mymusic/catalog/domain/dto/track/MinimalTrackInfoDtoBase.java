package com.mymusic.catalog.domain.dto.track;

import com.mymusic.catalog.domain.interfaces.Durable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public abstract class MinimalTrackInfoDtoBase implements Durable {
    Long id;
    String name;
    Integer duration;
    boolean liked;
}
