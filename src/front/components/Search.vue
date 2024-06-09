<script setup lang="ts">
import { ref, computed } from 'vue'

const selectedValue = ref(0);
const searchQuery = ref('');

const selectedLabel = computed(() => {
  switch (selectedValue.value) {
    case 0:
      return 'Артист'
    case 1:
      return 'Альбом'
    case 2:
      return 'Плейлист'
    default:
      return 'Неизвестно'
  }
})

const submitSearch = () => {
  console.log('Search Query:', searchQuery.value);
  console.log('Selected Value:', selectedValue.value);
  const router = useRouter();
  router.push({
    path: '/search',
    query: {
      query: searchQuery.value,
      type: selectedValue.value
    }
  }).then(() => searchQuery.value = '');
}

</script>

<template>
  <div class="search-container">
    <input v-model="searchQuery" class="icon-rtl" placeholder="Поиск в медиатеке"/>
    <select v-model="selectedValue">
      <option :value="0">Артист</option>
      <option :value="1">Альбом</option>
      <option :value="2">Плейлист</option>
    </select>
    <button @click.prevent="submitSearch"><i class="pi pi-search"></i>Поиск</button>
  </div>
</template>

<style scoped>
@import "primeicons/primeicons.css";

button {
  display: flex;
  flex-direction: row;
  gap: 1rem
}

.search-container {
  flex-grow: 1;
  display: flex;
  gap: 1rem;
}

input {
  width: 100%;
}

select {
  padding: 0.5rem;
  border-radius: 999px;
}

select option:checked {
  background-color: var(--accent-color);
  color: var(--primary-text-color);
}

select option:hover {
  background-color: transparent;
  border-width: 1px;
  border-color: var(--accent-color);
}

</style>