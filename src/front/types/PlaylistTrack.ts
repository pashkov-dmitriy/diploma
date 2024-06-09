import type Track from "~/types/Track";

export default interface PlaylistTrack extends Track {
    album: {
        id: number,
        name: string,
        coverUrl: string,
        artist: {
            id: number,
            name: string
        },
    }
}