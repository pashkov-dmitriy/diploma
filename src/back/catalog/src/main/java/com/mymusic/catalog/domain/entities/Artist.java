package com.mymusic.catalog.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Artist {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;
    @FullTextField
    @NonNull
    private String name;
    @NonNull
    @Column(length = 512)
    private String bio;
    private String imageUrl;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Album> albums;

    @Override
    public String toString() {
        return this.name;
    }
}
