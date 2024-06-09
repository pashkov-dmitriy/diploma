<script setup lang="ts">
import type {TrackList} from "~/types/TrackList";
import {isAlbumTrack, isPlaylistTrack} from "~/types/TrackList";
import type AlbumTrack from "../types/AlbumTrack";
import {addSeconds, format} from "date-fns";
import AddToPlaylistWindow from "~/components/AddToPlaylistWindow.vue";
import {useAudioStore} from "~/store/audio-store";
import {useTrackStore} from "~/store/track-store";
import {useAccessTokenStore} from "~/store/access-token-store";

interface TrackListProps {
  tracks: TrackList
}

const props = defineProps<TrackListProps>();
console.log(props.tracks);

const emits = defineEmits(['modal-opened', 'modal-closed']);
const {track, setTrack} = useTrackStore();
const {setPlaying, setCurrentPos} = useAudioStore();

const selectedTracks = ref([] as number[]);
const isModalVisible = ref(false);

const convertToMmSs = (seconds: number) => {
  console.log(seconds)
  const date = new Date(0);
  return format(addSeconds(0, seconds), 'mm:ss');
}

const changeLike = (id: number) => {
  props.tracks.filter((track) => track.id == id)
      .map(track => {
        track.liked = !track.liked;
        return track;
      });
}

const openModal = (trackId: number) => {
  emits('modal-opened');
  isModalVisible.value = true;
}

const closeModal = () => {
  emits('modal-closed');
  isModalVisible.value = false;
  selectedTracks.value = [];
}

const addToPlaylist = (id: number) => {
  selectedTracks.value.push(id);
  isModalVisible.value = true;
  console.log(selectedTracks.value);
}

const setPlay = async (id: number, name: string, coverUrl: string | undefined = undefined, artist: string | undefined = undefined) => {
  const {access} = useAccessTokenStore();
  const trackInfo = await $fetch<{
    id: number,
    name: string,
    coverUrl: string,
    artist: string}>(`http://localhost:8080/tracks/${id}/info`, {
      method: 'GET',
      headers: access != '' ? {
        Authorization: `Bearer ${access}`
      } : {}
  });
  setTrack(trackInfo.id, trackInfo.name, trackInfo.artist, trackInfo.coverUrl);
  setCurrentPos(id);
  setPlaying(true);
}

</script>

<template>
  <AddToPlaylistWindow
      :is-visible="isModalVisible"
      :track-ids="selectedTracks"
      @close="closeModal"
  ></AddToPlaylistWindow>
  <ul class="tracklist-container">
    <li v-for="track in tracks" :key="track.id" class="tracklist-item">
      <div class="item-left">
        <button @click="setPlay(track.id, track.name)" class="play-btn">
          <i class="pi pi-play"></i>
        </button>
        <span v-if="isAlbumTrack(track)" class="position">{{(track as AlbumTrack).position}}</span>
        <div class="track__info">
          <p>{{track.name}}</p>
          <div class="playlistOnly">

          </div>
        </div>
      </div>
      <div class="item-right">
        <span>{{convertToMmSs(track.duration)}}</span>
        <button @click="addToPlaylist(track.id)" class="add-to-playlist">
          <i class="pi pi-plus"></i>
        </button>
        <button @click="changeLike(track.id)">
          <i v-if="track.liked" class="pi pi-heart-fill"></i>
          <i v-else class="pi pi-heart"></i>
        </button>
      </div>
    </li>
  </ul>
</template>

<style scoped>
@import "primeicons/primeicons.css";

.play-btn {
  margin: 0.2rem;
}

.pi-play{
  font-size: 1.5rem;
}

.item-left {
  display: flex;
  flex-direction: row;
}

.item-right {
  display: flex;
  align-items: center;
  gap: 1rem;
  flex-direction: row;
}

.add-to-playlist {
  padding: 0;
  margin: 0;
  background-color: transparent;
}

button {
  padding: 0;
  margin: 0;
  background-color: transparent;
}

button:hover {
  text-align: center;
  cursor: pointer;
}

i {
  height: 100%;
  width: auto;
}

.tracklist-container {
  font-weight: lighter;
  padding: 1rem;
  background-color: var(--accent-color);
  border-radius: 10px;
}

.tracklist-item {
  padding: 0.5rem;
  margin: 0;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  line-height: 1;
  border-bottom: 0.1rem solid var(--secondary-color);
}

p {
  margin: 0;
}

.position {
  align-self: center;
  margin-right: 1rem;
}
</style>