<template>
  <div class="collage">
    <div v-for="(album, index) in albums" :key="index" class="collage-item">
      <img :src="album.coverUrl" :alt="album.name" class="collage-img" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

interface Album {
    name: string;
    coverUrl: string;
}

export default defineComponent({
    name: 'Collage',
    props: {
        albums: {
            type: Array as () => Album[],
            required: true
        }
    }
});
</script>

<style scoped>
.collage {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(2, 1fr);
    gap: 10px;
}

.collage-item {
    width: 100%;
    height: 100%;
}

.collage-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
</style>