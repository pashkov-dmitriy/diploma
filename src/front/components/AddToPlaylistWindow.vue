<script setup lang="ts">
import type Playlist from "~/types/Playlist";

const props = defineProps<{trackIds: number[], isVisible: boolean}>()
const emits = defineEmits(['close']);

const visible = ref(props.isVisible);
const trackIds = ref([] as number[]);
const playlists = reactive([] as Playlist[]);

const loadPlaylists = async () => {
  try {
    const {data, pending, error} = await $api<Playlist[]>('http://localhost:8080/playlists');
    console.log(error.value)
    if (!error.value) {
      if (data) {
        playlists.push(...data.value!);
      }
      else {
        console.log('data is missing');
      }
    }
    else {
      console.log(error.value);
    }
  }
  catch (error) {
    console.log(error);
  }
}

const getImageSrc = (playlist: Playlist) => playlist.coverUrl || '/no-cover.jpg';

const addToPlaylist = () => {

}

const show = async (tracks: number[]) => {
  trackIds.value = tracks;
  visible.value = true;
}

const close = () => {
  emits('close');
}

watch(() => props.isVisible, async () => {
  if (playlists.length == 0) {
    await loadPlaylists();
  }
})

</script>

<template>
<div v-if="props.isVisible" class="window-container">
  <div class="header">
    <h1 class="header__title">Добавить в плейлист</h1>
    <button @click="close" class="header__cancel">
      <i class="pi pi-times close-icon"></i>
    </button>
  </div>
  <p>Выберите плейлист, в который хотите добавить треки</p>
  <div class="list-wrapper">
    <ul class="list">
      <li v-for="pl in playlists" :key="pl.id" class="playlist-item">
        <button class="playlist-button">
          <img :src="getImageSrc(pl)" alt="No cover" class="cover">
          <span>{{pl.name}}</span>
        </button>
      </li>
    </ul>
  </div>
</div>
</template>

<style scoped>
@import "primeicons/primeicons.css";

.window-container {
  position: absolute;
  left: 20vw;
  top: 10vh;
  z-index: 999;
  height: 80vh;
  width: 60vw;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  background-color: var(--accent-color);
  box-shadow: 0px 0px 0px 999px rgba(0,0,0,0.5);
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.header__cancel {
  width: 2.5rem;
  height: 2.5rem;
  background-color: transparent;
  padding: 0;
  margin: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.close-icon, .close-icon::before {
  font-size: 2rem;
  flex-grow: 1;
}

.list-wrapper {
  flex-grow: 1;
  width: 100%;
  display: flex;
  flex-direction: column;
  overflow-y: auto;
}

.list {
  flex-grow: 0;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  overflow-y: auto;
}

.playlist-button {
  padding: 0.5rem;
  flex-grow: 1;
  font-size: 1rem;
  display: flex;
  flex-direction: row;
  gap: 1rem;
  align-items: center;
  border-radius: 10px;
  background-color: var(--gray-50);
  box-shadow: 0px 0px 10px 1px rgba(0,0,0,0.1);
}

.playlist-button > img {
  height: 3rem;
  width: auto;
  border-radius: 3px;
}

.playlist-item {
  flex-grow: 1;
  display: flex;
  align-items: center;
  border-radius: 10px;
}
</style>