package com.mymusic.catalog.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Data
@Table(name = "track")
public class Track {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Long id;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;
    @NonNull
    private Integer position;
    @NonNull
    private String name;
    @NonNull
    private Integer duration;
    @ManyToMany(mappedBy = "tracks")
    private Set<Playlist> playlists = new HashSet<>();
}
