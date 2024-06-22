<template>
  <div class="w-3/4 p-4">
    <div v-if="loading" class="text-center">
      Loading products...
    </div>
    <div v-else class="grid grid-cols-3 gap-4">
      <ProductCardComp v-for="product in products" :key="product.id" :product="product" />
    </div>
    <div class="mt-4 flex justify-center">
      <button v-on:click="prevPage" :disabled="page === 0" class="mr-2 p-2 border rounded">Previous</button>
      <button v-on:click="nextPage" class="p-2 border rounded">Next</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ProductCardComp from "./ProductCardComp.vue";

export default {
  components: {
    ProductCardComp,
  },
  data() {
    return {
      products: [],
      loading: true,
      page: 0,
      size: 12,
    };
  },
  created() {
    this.fetchProducts();
  },
  methods: {
    async fetchProducts() {
      this.loading = true;
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/products`,
          {
            params: {
              size: this.size,
              page: this.page,
            },
          },
        );
        this.products = response.data.content; // Adjust based on actual response structure
      } catch (error) {
        console.error("Error fetching products:", error);
      } finally {
        this.loading = false;
      }
    },
    nextPage() {
      this.page += 1;
      this.fetchProducts();
    },
    prevPage() {
      if (this.page > 0) {
        this.page -= 1;
        this.fetchProducts();
      }
    },
  },
};
</script>

<style scoped>
/* Add any specific styles for your product grid here */
</style>
