<template>
  <v-app>
    <NavigationBar />
    <v-content class="background">
      <router-view>
      </router-view>
    </v-content>
  </v-app>
</template>


<script>
import NavigationBar from "./components/NavigationBar.vue";
export default {
  name: "App",
  el: "#app",
  components: {
    NavigationBar,
  }
};
</script>

<style scoped>
.background {
  background: url('./assets/background.jpg');
  background-size: cover;
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
}
</style>