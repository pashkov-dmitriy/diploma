package com.mymusic.catalog.domain.dto.playlist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDetailsRequestDto {
    private String name;
    private String description;
    private String cover;
}
