<template>
  <Header />
  <RouterView />
  <HomePage />
  <Footer />
</template>

<script>
import HomePage from "@/components/HomePage.vue";
import Footer from "@/components/FooterComp.vue";
import Header from "@/components/Header.vue";

export default {
  components: { Header, HomePage, Footer },
};
</script>

<style></style>
