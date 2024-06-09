<script setup lang="ts">

import {CollectionType} from "~/types/CollectionType";
import type Playlist from "~/types/Playlist";
import type Track from "~/types/Track";

definePageMeta({
  middleware: "auth-check"
});

const playlists = reactive([] as Playlist[]);
const likes = ref([] as Track[])

onMounted(() => loadPlaylists());

const loadPlaylists = async () => {
  const {data, pending, error} = await $api<Playlist[]>('http://localhost:8080/playlists', {
    method: 'GET'
  });
  console.log(data.value)
  if (data.value) {
    playlists.push(...data.value!);
  }
}

const getImageSrc = (playlist: Playlist) => playlist.coverUrl || '/no-cover.jpg';

</script>

<template>
  <div class="home-container">
    <div class="user-playlists">
      <h1 class="playlists">Ваши плейлисты</h1>
      <div v-if="playlists.length == 0" class="no-content">
        <h3>Пользователь пока не создал плейлистов</h3>
      </div>
      <ul class="list" v-if="playlists.length > 0">
        <li v-for="pl in playlists" :key="pl.id">
          <CollectionCard
              :type="CollectionType.PLAYLIST"
              :id="pl.id"
              :name="pl.name"
              :cover-url="getImageSrc(pl)"
          ></CollectionCard>
        </li>
      </ul>
    </div>
    <div class="user-likes">
      <h1 class="likes">Ваши последние лайки</h1>
      <div v-if="likes.length == 0" class="no-content">
        <h3>Пользователь пока не создал плейлистов</h3>
      </div>
    </div>
  </div>
</template>

<style scoped>
h1 {
  font-size: 3rem;
  font-weight: bold;
}
.home-container {
  height: 100%;
  margin: 0 5rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 3rem
}

.user-playlists {
  width: 100%;
}

.user-likes {
  height: 100%;
  width: 100%;
}

.no-content {
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.list {
  display: flex;
  flex-direction: row;
  overflow-y: auto;
  gap: 5%;
}

.list > li {
  width: 20%;
}

</style>