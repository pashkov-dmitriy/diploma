<script setup lang="ts">
onMounted(() => loadUser());

interface User {
  id: number,
  name: string,
  profileImageUrl: string
}

const user = reactive({} as User)

const {data, pending, error} = await $api<User>('http://localhost:8080/profile');

</script>

<template>

</template>

<style scoped>

</style>