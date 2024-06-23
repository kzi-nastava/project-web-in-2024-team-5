<template>
  <Header />
  <ProductsGridSection :filters="filters" @update-filters="updateFilters" />
  <Footer />
</template>

<script>
import Header from "@/components/Header.vue";
import ProductsGridSection from "@/components/ProductsSection.vue";
import Footer from "@/components/FooterComp.vue";

export default {
  components: { Header, ProductsGridSection, Footer },
  data() {
    return {
      filters: {
        selectedCategory: "",
        minPrice: null,
        maxPrice: null,
        typeOfSale: "",
        searchTerm: this.$route.query.search || "",
      },
    };
  },
  watch: {
    "$route.query.search": {
      handler(newSearchTerm) {
        this.filters.searchTerm = newSearchTerm;
      },
      immediate: true,
    },
  },
  methods: {
    updateFilters({minPrice, maxPrice, category, typeOfSale}) {
      this.filters.selectedCategory = category;
      this.filters.minPrice = minPrice;
      this.filters.maxPrice = maxPrice;
      this.filters.typeOfSale = typeOfSale;
    },
  },
};
</script>
