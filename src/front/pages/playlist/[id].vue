<script setup lang="ts">
import type {TrackList} from "~/types/TrackList";
import type PlaylistTrack from "~/types/PlaylistTrack";

const OWNER_TYPE = 'user';

interface Playlist {
  id: number,
  name: string,
  owner: {
    id: number,
    name: string
  },
  description?: string,
  coverUrl?: string | null,
  tracks?: PlaylistTrack[]
}

let collection = ref({} as Playlist);
const {id} = useRoute().params;

const loadInfo = async () => {
  const {data, pending, error} = await $api<Playlist>('http://localhost:8080/playlists/' + id);
  if (data) {
    console.log(data);
    collection.value = data.value as Playlist;
  }
}

const loadTracks = async () => {
  console.log('sf')
  const {data, pending, error} = await $api<PlaylistTrack[]>(`http://localhost:8080/playlists/${id}/tracks`);
  if (data?.value) {
    const v = data.value;
    collection.value.tracks = data.value as PlaylistTrack[];
  }
}

await loadInfo();
await loadTracks();

</script>

<template>
  <div class="album-container">
    <div class="album__cover">
      <div v-if="collection.coverUrl" class="cover">
        <img :src="collection?.coverUrl" alt="">
      </div>
      <div v-else>
        <div>{{error}}</div>
      </div>
    </div>
    <div class="album__info">
      <CollectionInfo
          :title="collection.name"
          :description="collection.description"
          :owner="collection.owner"
          :owner-type="OWNER_TYPE"
      ></CollectionInfo>
      <div class="tracks">
        <Tracklist :tracks="collection.tracks"></Tracklist>
      </div>
    </div>
  </div>
</template>

<style scoped>
img {
  width: 100%;
  height: auto;
  border-radius: 10px;
}

.album-container {
  height: 100%;
  width: 100%;
  display: grid;
  grid-template-columns: 2fr 3fr;
  gap: 1rem;
}

.album__cover {
  align-self: center;
  justify-self: center;
  padding: 0.5em;
}
</style>