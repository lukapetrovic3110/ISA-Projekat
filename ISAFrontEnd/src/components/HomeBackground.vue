<template>
  <v-container fluid fill-height class="background">
  </v-container>
</template>

<script>
export default {
    name: 'HomeBackground'
}
</script>

<style scoped>
.background {
  background: url('../assets/background.jpg');
  background-size: cover;
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
}
</style>