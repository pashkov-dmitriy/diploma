package com.mymusic.catalog.services;

import com.mymusic.catalog.domain.dto.artist.ArtistDto;
import com.mymusic.catalog.domain.dto.artist.ArtistPreviewDto;
import com.mymusic.catalog.mappers.AlbumMapper;
import com.mymusic.catalog.mappers.ArtistMapper;
import com.mymusic.catalog.repos.ArtistsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistsRepository artistsRepository;

    public ArtistService(ArtistsRepository artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    public List<ArtistPreviewDto> getArtistsByName(String name) {
        return artistsRepository.findByNameContaining(name)
                .stream().map(artist -> {
                    return ArtistMapper.INSTANCE.artistToArtistPreviewDto(artist);
                }).toList();
    }

    public Optional<ArtistDto> getArtistById(Long id) {
        return artistsRepository.findById(id)
                .map(artist -> {
                    ArtistDto artistDto = new ArtistDto();
                    artistDto.setId(artist.getId());
                    artistDto.setName(artist.getName());
                    artistDto.setBio(artist.getBio());
                    artistDto.setImageUrl(artist.getImageUrl());
                    artistDto.setAlbums(AlbumMapper.INSTANCE.albumsToAlbumOfArtistPreviewDtos(artist.getAlbums()));
                    return artistDto;
                });
    }

    public Optional<ArtistDto> getArtistWithAlbums(Long id) {
        return artistsRepository.findById(id)
                .map(artist -> {
                    ArtistDto artistDto = ArtistMapper.INSTANCE.artistToArtistDto(artist);
                    artistDto.setId(artist.getId());
                    return artistDto;
                });
    }
}
