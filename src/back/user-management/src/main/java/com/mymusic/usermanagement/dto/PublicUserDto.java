package com.mymusic.usermanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class PublicUserDto {
    private Long id;
    private String username;
    private String description;
    private String profilePicUri;
}
