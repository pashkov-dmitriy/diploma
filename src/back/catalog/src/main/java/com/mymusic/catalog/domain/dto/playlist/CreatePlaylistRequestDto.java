package com.mymusic.catalog.domain.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaylistRequestDto {
    String name;
    String description;
    Long ownerId;
    String cover;
    List<Long> trackIds;
}
