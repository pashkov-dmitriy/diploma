
interface TrackInPlaylist {
    id: number,
    name: string,
    duration: number,
    artist: {
        id: number,
        name: string
    },
    album: {
        id: number,
        name: string
    },
}

interface AudioState {
    playlist: TrackInPlaylist[],
    currentPos: null | number,
    isPlaying: boolean
}

export const useAudioStore = defineStore('audio', () => {
    const audioState = reactive<AudioState>({
        playlist: [] as TrackInPlaylist[],
        currentPos: null,
        isPlaying: false
    });

    const currentPos = computed(() => {
        return audioState.currentPos ? audioState.playlist[audioState.currentPos] : null;
    })

    function setPlaylist(tracks: TrackInPlaylist[]) {
        audioState.playlist = tracks;
        audioState.currentPos = 0;
    }

    function setCurrentPos(index: number) {
        // if (index >= 0 &&  index < audioState.playlist.length) {
        //     console.log("ffa")
        //     audioState.currentPos = index;
        // }
        // console.log(audioState.currentPos);
        audioState.currentPos = index;
        console.log(audioState.currentPos);
    }

    function setPlaying(isPlaying: boolean) {
        audioState.isPlaying = isPlaying;
    }

    function playNextTrack() {
        if (audioState.currentPos !== null && audioState.currentPos < audioState.playlist.length - 1) {
            audioState.currentPos++;
        }
        if (audioState.currentPos == audioState.playlist.length - 1) {
            audioState.currentPos = 0;
        }
        else {
            setPlaying(false);
        }
    }
    function playPreviousTrack() {
        if (audioState.currentPos !== null && audioState.currentPos > 0) {
            audioState.currentPos--;
        }
    }

    return {audioState, setPlaying, setPlaylist, setCurrentPos, playNextTrack, playPreviousTrack};
})