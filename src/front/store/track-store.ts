export const useTrackStore = defineStore('track', () => {
    const track = reactive({} as {
        id: number,
        name: string,
        artist?: string,
        coverUrl?:string
    })

    function setTrack(id: number, name: string, artist: string | undefined = undefined, coverUrl: string | undefined = undefined) {
        track.id = id;
        track.name = name;
        track.coverUrl = coverUrl;
        track.artist = artist;
    }

    return {track, setTrack};
})