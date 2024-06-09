package com.mymusic.likes.entities;

import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public  record Like(
        @Id Long id,
        @NonNull Long userId,
        @NonNull Long trackId
) { }
