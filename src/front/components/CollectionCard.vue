<script setup lang="ts">
import type {CollectionType} from "~/types/CollectionType";

interface CollectionProps {
  type: CollectionType
  id: number,
  name: string,
  genre?: string,
  date?: number,
  coverUrl?: string,
}

onMounted(() => {
  console.log('Component Mounted');
  console.log(props);
});

const noCover = '~/public/no-cover.jpg';
const props = defineProps<CollectionProps>();
console.log(props)
</script>

<template>
  <div class="card">
    <img :src="props.coverUrl ? props.coverUrl : noCover" alt="Cover">
    <router-link :to="`/${type}/${props.id}`">{{props.name}}</router-link>
    <h5 v-if="props.genre">{{props.genre}}</h5>
    <h5 v-if="props.date">{{props.date}}</h5>
  </div>
</template>

<style scoped>
img {
  margin-bottom: 0.2em;
}

.card {
  padding: 0.5em;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

h5 {
  margin: 0;
  line-height: 1.5;
  font-size: 0.7em;
}

img {
  width: 100%;
  height: auto;
  border-radius: 10px;
}
</style>