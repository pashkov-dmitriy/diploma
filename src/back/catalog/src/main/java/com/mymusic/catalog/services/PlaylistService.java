package com.mymusic.catalog.services;

import com.mymusic.catalog.clients.UserManagementClient;
import com.mymusic.catalog.domain.dto.owner.OwnerPreviewDto;
import com.mymusic.catalog.domain.dto.playlist.*;
import com.mymusic.catalog.domain.dto.track.TrackPreviewInPlaylistDto;
import com.mymusic.catalog.domain.entities.Playlist;
import com.mymusic.catalog.exceptions.PlaylistNotFoundException;
import com.mymusic.catalog.exceptions.UserNotFoundException;
import com.mymusic.catalog.mappers.PlaylistMapper;
import com.mymusic.catalog.repos.PlaylistRepository;
import com.mymusic.catalog.domain.entities.Track;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final Logger LOGGER = LoggerFactory.getLogger(PlaylistService.class);

    private final PlaylistRepository playlistRepository;
    private final TrackService trackService;
    //private final LikesClient likesClient;
    private final UserManagementClient userManagementClient;

    public List<PlaylistPreviewDto> getPlaylistsByUserId(Long id) {
        var l =  playlistRepository.getPlaylistsByUserId(id);
        var pl = l.stream().map(playlist -> {
            LOGGER.debug(playlist.getName());
            return new PlaylistPreviewDto(
                    playlist.getId(),
                    playlist.getName(),
                    playlist.getCoverUrl()
            );
        }).toList();

        return pl;
    }

    public Set<Track> getTracksByPlaylistId(long playlistId) {
        return playlistRepository.findTracksByPlaylistId(playlistId)
                .orElse(Collections.emptySet());
    }

    public Optional<Playlist> getPlaylistById(long playlistId) {
        return playlistRepository.findById(playlistId);
    }

    public Long createNewPlaylist(CreatePlaylistRequestDto newPlaylist) throws Exception {
        var userExists = userManagementClient
                .isUserExists(newPlaylist.getOwnerId());

        if (!userExists.isExists()) {
            throw new UserNotFoundException(newPlaylist.getOwnerId());
        }

        Playlist playlist = new Playlist();

        playlist.setName(newPlaylist.getName());
        playlist.setDescription(newPlaylist.getDescription());
        playlist.setCoverUrl(newPlaylist.getCover());
        playlist.setUserId(newPlaylist.getOwnerId());

        playlist = playlistRepository.save(playlist);
        addTracksToPlaylist(playlist.getId(), newPlaylist.getTrackIds());

        return playlist.getId();
    }

    public void removePlaylistById(Long id) throws Exception {
        Playlist playlist = playlistRepository.findPlaylistById(id)
                .orElseThrow(() -> new Exception("Playlist not found"));

        playlistRepository.delete(playlist);
    }

    public Optional<PlaylistDto> getPlaylistDtoById(Long id) {

        Optional<Playlist> playlistOptional = playlistRepository.findPlaylistById(id);

        return playlistOptional.map(playlist -> {
            PlaylistDto playlistDto = new PlaylistDto();
            playlistDto.setId(playlist.getId());
            playlistDto.setName(playlist.getName());
            playlistDto.setDescription(playlist.getDescription());
            playlistDto.setUserId(playlist.getUserId());
            playlistDto.setCoverUrl(playlist.getCoverUrl());

            Optional<List<TrackPreviewInPlaylistDto>> tracklistOptional = trackService.getTracksWithCoverInPlaylist(id);
            List<TrackPreviewInPlaylistDto> tracks = tracklistOptional.orElseGet(ArrayList::new);
            playlistDto.setTracks(tracks);

            return playlistDto;
        });
    }

    public PlaylistTracklistDto addTracksToPlaylist(Long id, Iterable<Long> trackIds) throws Exception {
        Playlist playlist = playlistRepository.findPlaylistById(id)
                .orElseThrow(() -> new Exception("Playlist not found"));

        List<Track> tracks = trackService.getTracksByIds(trackIds);

        for (Track t : tracks) {
            playlist.addTrack(t);
        }

        playlist = playlistRepository.save(playlist);
        return PlaylistMapper.INSTANCE.toPlaylistTracklistDto(playlist);
    }

    public PlaylistTracklistDto removeTracksFromPlaylist(Long id, Iterable<Long> trackIds) throws Exception {
        Playlist playlist = playlistRepository.findPlaylistById(id)
                .orElseThrow(() -> new Exception("Playlist not found"));

        List<Track> tracks = trackService.getTracksByIds(trackIds);

        for (Track t : tracks) {
            playlist.removeTrack(t);
        }

        playlist = playlistRepository.save(playlist);
        return PlaylistMapper.INSTANCE.toPlaylistTracklistDto(playlist);
    }

    public List<PlaylistPreviewDto> getRandomPlaylistsContainingLikedTracks() {
        return null;
    }

    public PlaylistDetailsDto getPlaylistInfoById(Long id)
            throws IllegalStateException, PlaylistNotFoundException, UserNotFoundException {
        Playlist playlist = playlistRepository.findPlaylistById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));

        OwnerPreviewDto owner = userManagementClient.getUserPreview(playlist.getUserId());

        PlaylistDetailsDto playlistInfo = PlaylistMapper.INSTANCE.playlistToPlaylistInfoDto(playlist);
        playlistInfo.setOwner(owner);

        return playlistInfo;
    }
    
    public PlaylistTracklistDto getPlaylistTracklist(Long playlistId) {
        Set<Track> tracks = playlistRepository.findTracksByPlaylistId(playlistId)
            .orElse(Collections.emptySet());
        PlaylistTracklistDto tracklist = new PlaylistTracklistDto();
        tracklist.setTracks(tracks);
        return tracklist;
    }

//    public PlaylistDetailsDto updatePlaylistDetails(Long id, UpdateDetailsRequestDto newDetails) {
//        Playlist playlist = playlistRepository.findPlaylistById(id)
//                .orElseThrow(() -> new PlaylistNotFoundException(id));
//
//        if
//    }
}
