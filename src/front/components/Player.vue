<script setup lang="ts">

import {useAudioStore} from "~/store/audio-store";
import {useTrackStore} from "~/store/track-store";

const {track, setTrack} = useTrackStore();
const {audioState, setPlaying, setPlaylist, setCurrentPos, playNextTrack, playPreviousTrack} = useAudioStore();
const isPlaying = computed(() => audioState.isPlaying);

const togglePlay = () => {
  if (isPlaying.value) {
    setPlaying(false);
  }
  if (!isPlaying.value) {
    setPlaying(true);
  }
}

</script>

<template>
  <div class="player-container">
    <div class="track-info">
      <div class="cover">
        <img :src="track.coverUrl" alt="">
      </div>
      <div class="track-title">
        <p>{{track.artist}}</p>
        <p>{{track.name}}</p>
      </div>
    </div>
<!--    <div class="controls">-->
<!--      <div class="playback-buttons">-->
<!--        <button class="prev">-->
<!--          <i class="pi pi-step-backward"></i>-->
<!--        </button>-->
<!--        <button @click="" class="play-pause">-->
<!--          <i v-if="!isPlaying" class="pi pi-play"></i>-->
<!--          <i v-else class="pi pi-pause"></i>-->
<!--        </button>-->
<!--        <button class="next">-->
<!--          <i class="pi pi-step-forward"></i>-->
<!--        </button>-->
<!--      </div>-->
<!--    </div>-->
    <audio :src="`http://localhost:8100/storage/tracks/${audioState.currentPos}`" controls ></audio>
  </div>
</template>

<style scoped>
@import "primeicons/primeicons.css";

img {
  border-radius: 5px;
  width: 50px;
  height: auto;
}

.player-container {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  border-radius: 10px;
  background-color: var(--accent-color);
  width: 100%;
  height: 100%;
  padding: 10px;
}

.track-title {
  display: flex;
  flex-direction: column;
}

.track-info {
  display: flex;
  flex-direction: row;
  margin-left: 10px;
  gap: 0.5rem;
}
</style>