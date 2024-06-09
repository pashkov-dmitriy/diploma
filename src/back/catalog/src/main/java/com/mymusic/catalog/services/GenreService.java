package com.mymusic.catalog.services;

import com.mymusic.catalog.domain.entities.Genre;
import com.mymusic.catalog.repos.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }
}
