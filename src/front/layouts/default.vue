<script setup lang="ts">
import {useAudioStore} from "~/store/audio-store";
import {useAccessTokenStore} from "~/store/access-token-store";

definePageMeta({
  middleware: 'auth-check'
})

const {clearAccessToken} = useAccessTokenStore();

const logout = () => {
  clearAccessToken();
  useRouter().push("/sign-in");
}

const toProfile = () => {
  useRouter().push('/user')
}

</script>

<template>
  <div class="wrapper">
    <div class="header">
      <div class="user">
        <div class="logout">
          <button @click="logout">
            <i class="pi pi-sign-out"></i>
          </button>
        </div>
        <div class="profile">
          <button @click="toProfile">
            <i class="pi pi-user"></i>
          </button>
        </div>
      </div>
      <Search></Search>
    </div>
    <div class="content">
      <slot/>
    </div>
    <div class="player">
      <Player></Player>
    </div>
  </div>
</template>

<style scoped>
@import "primeicons/primeicons.css";

.wrapper {
  height: 100%;
  padding: 1em;
  display: flex;
  flex-direction: column;
  gap: 1em;
}

.user {
  display: flex;
  flex-direction: row;
  gap: 1em;
}

.content {
  width: 100%;
  height: 100%;
}

.header {
  display: flex;
  flex-direction: row;
  gap: 1em;
}

.player {
  height: 200px;
  width: 320px;
  position: fixed;
  bottom: -50px;
  left: 4.4rem;
}
</style>