<script setup lang="ts">
import {computed} from "vue";

definePageMeta({
  layout: 'default',
  middleware: "auth-check"
})

interface SearchParams {
  query: string;
  type: number;
}

const route = useRoute();
const searchParams: SearchParams = {
  query: route.query.query as string,
  type: parseInt(route.query.type as string)
};

watch(() => route.query, async () => {
  return await $api<any[]>(`http://localhost:8080/search?query=${searchParams.query}&type=${searchParams.type}`);
})

const searchType = computed(() => {
  switch (searchParams.type) {
    case 0:
      return 'artist'
    case 1:
      return 'album'
    case 2:
      return 'playlist'
    default:
      return 'Неизвестно'
  }
})

const {data, pending, error} = await $api<any[]>(`http://localhost:8080/search?query=${searchParams.query}&type=${searchParams.type}`);
console.log(data.value)
</script>

<template>
  <div>
    <div v-if="pending" class="pending">
      <i class="pi pi-spin pi-spinner"></i>
    </div>
    <div v-else class="results">
      <h1>Результаты поиска</h1>
      <ul>
        <li v-for="item in data">
          <router-link v-if="searchParams.type === 0" :to="`/artist/${item.id}`">{{ item.name }}</router-link>
          <router-link v-else-if="searchParams.type === 1" :to="`/album/${item.id}`">{{ item.name }}</router-link>
          <router-link v-else-if="searchParams.type === 2" :to="`/playlist/${item.id}`">{{ item.name }}</router-link>
        </li>
      </ul>
      <div v-if="data?.length == 0" class="empty">
        По вашему запросу ничего не найдено
      </div>
    </div>
  </div>
</template>

<style scoped>
.results {
  margin: 0 7rem;
}

h1 {
  color: var(--secondary-text-color);
}

ul {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

a {
  color: var(--primary-text-color)
}
</style>