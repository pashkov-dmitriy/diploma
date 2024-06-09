package com.mymusic.catalog.repos;

import com.mymusic.catalog.domain.entities.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {

    List<Album> findAlbumsByArtistId(Long artistId);

    List<Album> findAlbumsByGenreId(Long genreId);
}
