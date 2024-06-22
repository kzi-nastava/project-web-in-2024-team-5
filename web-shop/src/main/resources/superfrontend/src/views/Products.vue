<template>
  <Header />
  <ProductsPage />
  <Footer />
</template>

<script>
import HomePage from "@/components/HomePage.vue";
import Sidebar from "@/components/Sidebar.vue";
import Header from "@/components/Header.vue";
import ProductsPage from "@/components/ProductsPage.vue";
import Footer from "@/components/FooterComp.vue";

export default {
  components: { Header, ProductsPage, Footer },
};
</script>

<style></style>

