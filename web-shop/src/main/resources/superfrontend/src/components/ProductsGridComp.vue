<template>
  <div class="w-3/4 p-4">
    <div v-if="loading" class="text-center">Loading products...</div>
    <div v-else class="grid grid-cols-3 gap-4">
      <ProductCardComp
        v-for="product in products"
        :key="product.id"
        :product="product"
      />
    </div>
    <div class="mt-4 flex justify-center">
      <button
        v-on:click="prevPage"
        :disabled="page === 0"
        class="mr-2 p-2 border rounded"
      >
        Previous
      </button>
      <button v-on:click="nextPage" class="p-2 border rounded">Next</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ProductCardComp from "@/components/ProductCardComp.vue";

export default {
  name: "ProductGridComp",
  components: {
    ProductCardComp,
  },
  props: {
    filters: {
      type: Object,
    },
  },
  data() {
    return {
      products: [],
      loading: true,
      page: 0,
      size: 12,
    };
  },
  watch: {
    filters: {
      handler(newFilters) {
        this.page = 0;
        this.fetchProducts(newFilters);
      },
      deep: true,
    },
  },
  created() {
    this.fetchProducts(this.filters);
  },
  methods: {
    async fetchProducts(filters) {
      this.loading = true;
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/products/filter",
          {
            params: {
              size: this.size,
              page: this.page,
              minPrice: filters.minPrice,
              maxPrice: filters.maxPrice,
              typeOfSale: filters.typeOfSale,
              category: filters.selectedCategory,
            },
          },
        );
        this.products = response.data;
      } catch (error) {
        console.error("Error fetching products:", error);
      } finally {
        this.loading = false;
      }
    },
    nextPage() {
      this.page += 1;
      this.fetchProducts(this.filters);
    },
    prevPage() {
      if (this.page > 0) {
        this.page -= 1;
        this.fetchProducts(this.filters);
      }
    },
  },
};
</script>
