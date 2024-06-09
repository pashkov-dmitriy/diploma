<script setup lang="ts">
import type {TrackList} from "~/types/TrackList";

const OWNER_TYPE = 'artist';

interface Collection {
  id: number,
  name: string,
  owner: {
    id: number,
    name: string
  },
  genre?: string,
  date?: number,
  description?: string,
  coverUrl?: string,
  tracks: TrackList
}

let collection = reactive({} as Collection);
let tracks = [] as TrackList;
const router = useRouter();
const {id} = useRoute().params;
console.log('sf')
const {data, pending, error} = await $api<Collection>('http://localhost:8080/albums/' + id);
console.log(data);
if (data) {
  collection = data?.value as Collection;
}

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
          :genre="collection.genre"
          :date="collection.date"
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