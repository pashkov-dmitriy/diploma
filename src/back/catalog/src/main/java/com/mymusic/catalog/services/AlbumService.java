package com.mymusic.catalog.services;

import com.mymusic.catalog.domain.dto.album.AlbumOfArtistPreviewDto;
import com.mymusic.catalog.domain.entities.Album;
import com.mymusic.catalog.mappers.AlbumMapper;
import com.mymusic.catalog.repos.AlbumRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Optional<Album> findAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    public List<AlbumOfArtistPreviewDto> findArtistAlbums(Long artistId) {
        return albumRepository.findAlbumsByArtistId(artistId)
                .stream().map(AlbumMapper.INSTANCE::albumToAlbumOfArtistPreviewDto)
                .toList();
    }

//    public List<AlbumDto> findAlbumsInGenre(Long genreId) {
//        return albumRepository.findAlbumsByGenreId(genreId)
//                .stream().map( album -> {
//                    AlbumDto albumDto = new AlbumDto();
//                    albumDto.setId(album.getId());
//                    albumDto.setGenre(album.getGenre());
//                    albumDto.setName(album.getName());
//                    albumDto.setReleaseDate(album.getReleaseDate());
//                    return albumDto;
//                }).toList();
//    }
}
