<template>
  <Header />
  <ProductPage />
  <Footer />
</template>

<script>
import HomePage from "@/components/HomePage.vue";
import Sidebar from "@/components/Sidebar.vue";
import Header from "@/components/Header.vue";
import ProductPage from "@/components/ProductPage.vue";
import Footer from "@/components/FooterComp.vue";

export default {
  components: { Header, ProductPage, Footer },
};
</script>

<style></style>
