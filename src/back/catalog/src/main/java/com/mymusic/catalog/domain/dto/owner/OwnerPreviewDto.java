package com.mymusic.catalog.domain.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerPreviewDto {
    @NonNull
    private Long id;
    @NonNull
    private String name;
}
