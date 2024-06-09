package com.mymusic.catalog.domain.dto.playlist;

import com.mymusic.catalog.domain.dto.owner.OwnerPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDetailsDto {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private OwnerPreviewDto owner;
    private String description;
    private String cover;

}
