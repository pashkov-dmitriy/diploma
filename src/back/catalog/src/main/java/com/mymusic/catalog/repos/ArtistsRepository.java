package com.mymusic.catalog.repos;

import com.mymusic.catalog.domain.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistsRepository extends JpaRepository<Artist, Long> {
    List<Artist> findArtistByName(String name);
    List<Artist> findByNameContaining(String name);
}
