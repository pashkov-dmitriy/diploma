<script setup lang="ts">
interface DescriptionProps {
  title: string,
  description?: string,
}

const props = defineProps<DescriptionProps>();
</script>

<template>
  <div class="collection-description-container">
    <h1 class="title">{{props.title}}</h1>
    <p v-if="description" class="description">{{props.description}}</p>
  </div>
</template>

<style scoped>
.collection-description-container {
  display: flex;
  flex-direction: column;
}

.title {
  margin: 0;
  font-size: xxx-large;
  font-weight: bold;
}

.description {
  color: var(--secondary-text-color);
  word-break: break-word;
}
</style>