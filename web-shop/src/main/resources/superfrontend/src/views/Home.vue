<template>
  <Header />
  <RouterView />
  <HomePage />
  <FooterComp />
</template>

<script>
import HomePage from "@/components/HomePage.vue";
import FooterComp from "@/components/FooterComp.vue";
import Header from "@/components/Header.vue";

export default {
  components: { Header, HomePage, FooterComp },
};
</script>

<style></style>
