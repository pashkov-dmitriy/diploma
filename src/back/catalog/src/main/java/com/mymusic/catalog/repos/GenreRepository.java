package com.mymusic.catalog.repos;

import com.mymusic.catalog.domain.entities.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
