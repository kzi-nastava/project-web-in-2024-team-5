<template>
  <Header />
  <ProductsGridSection />
  <Footer />
</template>

<script>
import Header from "@/components/Header.vue";
import ProductsGridSection from "@/components/ProductsSection.vue";
import Footer from "@/components/FooterComp.vue";

export default {
  components: { Header, ProductsGridSection, Footer },
};
</script>

<style></style>
