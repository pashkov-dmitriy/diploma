<script setup lang="ts">

interface DescriptionProps {
  title: string,
  description?: string,
  owner: {
    id: number,
    name: string
  },
  ownerType: 'artist' | 'user',
  genre?: string,
  date?: number
}

const props = defineProps<DescriptionProps>();

const ownerLink = computed(() => {
  switch (props.ownerType) {
    case 'artist':
      return '/artist/'+props.owner.id;
    case 'user':
      return '/user/'+props.owner.id;
  }
})
</script>

<template>
  <div class="collection-description-container">
    <div class="title">
      <h1 class="name">{{props.title}}</h1>
      <router-link class="ownerLink" :to="ownerLink">{{props.owner.name}}</router-link>
      <div v-if="props.genre" class="album-only">
        <h3 v-if="props.date" class="date">{{props.date}}</h3>
        <i class="separator pi pi-circle"></i>
        <h3 v-if="props.genre" class="genre">{{props.genre}}</h3>
      </div>
    </div>
    <p v-if="description" class="description">{{props.description}}</p>
  </div>
</template>

<style scoped>
@import "primeicons/primeicons.css";

.collection-description-container {
  display: flex;
  flex-direction: column;
}

.ownerLink {
  font-size: 2rem;
}

.date, .genre {
  font-size: 1rem;
  font-weight: normal;
}

.description {
  font-size: 1rem;
}

.album-only {
  display: flex;
  flex-direction: row;
  gap: 1rem;
  align-items: center;
}

.separator {
  font-size: 1rem;
  color: var(--secondary-text-color);
}
</style>