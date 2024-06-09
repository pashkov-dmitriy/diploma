package com.mymusic.catalog.domain.dto.playlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mymusic.catalog.domain.dto.track.TrackPreviewInPlaylistDto;
import com.mymusic.catalog.domain.entities.Track;
import com.mymusic.catalog.domain.interfaces.ItemCollection;
import com.mymusic.catalog.mappers.TrackMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Setter
@NoArgsConstructor
public class PlaylistTracklistDto implements ItemCollection<TrackPreviewInPlaylistDto> {

    @Getter
    @JsonProperty
    private List<TrackPreviewInPlaylistDto> tracks;

    @Override
    @JsonIgnore
    public Collection<TrackPreviewInPlaylistDto> getItems() {
        return tracks;
    }


    public void setTracks(Set<Track> tracks) {
        TrackMapper mapper = TrackMapper.INSTANCE;
        this.tracks = tracks.stream()
                .map(mapper::toTrackPreviewInPlaylistDto)
                .toList();
    }
}
