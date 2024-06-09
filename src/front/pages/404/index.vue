<script setup lang="ts">
definePageMeta({
  layout: 'auth'
})
</script>

<template>
  <div>
    <h1>Не удается найти страницу</h1>
    <button @click="useRouter().go(-2)">Назад</button>
  </div>
</template>

<style scoped>
div {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>