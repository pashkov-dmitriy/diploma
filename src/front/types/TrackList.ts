import type Track from '~/types/Track';
import type AlbumTrack from "~/types/AlbumTrack";
import type PlaylistTrack from "~/types/PlaylistTrack";

export type TrackList = AlbumTrack[] | PlaylistTrack[];

export function isAlbumTrack(track: Track): track is AlbumTrack {
    return (track as AlbumTrack).position !== undefined;
}

export function isPlaylistTrack(track: Track): track is PlaylistTrack {
    return (track as PlaylistTrack).album !== undefined;
}