package com.mymusic.catalog.domain.dto.track;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistTrackModifyDto {
    List<Long> trackIds;
}
