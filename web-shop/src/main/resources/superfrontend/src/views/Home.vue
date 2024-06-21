<template>
  <Header />
  <RouterView />
  <HomePage />
</template>

<script>
import HomePage from "@/components/HomePage.vue";
import Header from "@/components/Header.vue";

export default {
  components: { Header, HomePage },
};
</script>

<style></style>

