<script setup lang="ts">

</script>

<template>
  <div class="search-container">
    <slot/>
  </div>
</template>

<style scoped>
.search-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>