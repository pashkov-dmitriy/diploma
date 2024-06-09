<script setup lang="ts">

import {useRoute} from "#imports";
import ProfileInfo from "~/components/ProfileInfo.vue";
import {CollectionType} from "~/types/CollectionType";

interface AlbumsPreview {
  id: number,
  name: string,
  coverUrl: string,
  genre: string,
  date: number
}

interface Artist {
  id: number,
  name: string,
  bio: string,
  imageUrl?: string,
  albums: AlbumsPreview[]
}

const mapAlbumPreviewToCollection = (album: AlbumsPreview) => {
  return {
    name: album.name,
    coverUrl: album.coverUrl,
  }
}

const router = useRouter();
const {id} = useRoute().params;
let artist = ref<Artist>({} as Artist);
const {data, error} = await $api<Artist>('http://localhost:8080/artists/' + id)
artist.value = data?.value as Artist;
if (data.value) {
  console.log(artist.value)
}
else {
  console.log(error)
}
</script>

<template>
  <div class="artist-container">
    <div class="artist__photo">
      <div v-if="artist.imageUrl">
        <img :src="artist.imageUrl" alt="">
      </div>
      <div v-else>

      </div>
    </div>
    <div class="artist__info">
      <ProfileInfo
        :title="artist.name"
        :description="artist.bio"
      ></ProfileInfo>
      <div class="albums">
        <div class="albums__title">Альбомы</div>
        <div class="albums-list__wrapper">
          <div class="albums-list">
            <div class="album__card" v-for="album in artist.albums" :key="album.id">
              <CollectionCard
                  :type="CollectionType.ALBUM"
                  :name="album.name"
                  :id="album.id"
                  :date="album.date"
                  :genre="album.genre"
                  :cover-url="album.coverUrl"
              ></CollectionCard>
            </div>
          </div>
        </div>
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

.artist-container {
  max-width: 100%;
  max-height: 100%;
  display: grid;
  grid-template-columns: 40% 60%;
  gap: 1em;
}

.artist__photo {
  align-self: center;
  justify-self: center;
  padding: 0.5em;
}

.artist__info {
  padding: 1em;
  max-width: 100%;
  display: flex;
  flex-direction: column;
}

.albums {
  width: 100%;
}

.albums-list__wrapper {
  max-width: 100%;
  overflow-x: auto;
}

.albums-list {
  width: 100%;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: row;
  overflow-x: auto; /* Сделать горизонтальную прокрутку */
  scroll-snap-type: x mandatory; /* Добавить прокрутку с защелкой (опционально) */
  -webkit-overflow-scrolling: touch; /* Обеспечить плавную прокрутку на устройствах iOS */
}

.albums__title {
  font-size: xx-large;
  font-weight: bold;
}

.album__card {
  min-width: 170px;
  max-width: 220px;
  padding: 10px;
}
</style>