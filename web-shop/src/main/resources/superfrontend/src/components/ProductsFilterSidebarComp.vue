<template>
  <div class="bg-[#FBFBF9] border-r px-8 py-6">
    <form @submit.prevent="applyFilter">
      <label for="minPrice">Min price:</label>
      <br />
      <input
        type="number"
        id="minPrice"
        name="minPrice"
        v-model.number="minPrice"
        class="w-full border-2 border-gray-200 rounded-md mb-4 px-2"
      />
      <br />
      <label for="maxPrice">Max price:</label>
      <br />
      <input
        type="number"
        id="maxPrice"
        name="maxPrice"
        v-model.number="maxPrice"
        class="w-full border-2 border-gray-200 rounded-md mb-6 px-2"
      />
      <h3 class="text-lg font-bold mb-4">Categories</h3>
      <div class="mb-2">
        <input
          type="radio"
          id="none"
          value=""
          v-model="selectedCategory"
          class="mr-2"
        />
        <label for="none">None</label>
      </div>
      <div
        v-for="category in categories"
        :key="category.categoryName"
        class="mb-2"
      >
        <input
          type="radio"
          :id="category.categoryName"
          :value="category.categoryName"
          v-model="selectedCategory"
          class="mr-2"
        />
        <label :for="category.categoryName">{{ category.categoryName }}</label>
      </div>
      <h3 class="text-lg font-bold my-4">Type of sale</h3>
      <div class="mb-2">
        <input
          type="radio"
          name="typeOfSale"
          id="AUCTION"
          value="AUCTION"
          v-model="typeOfSale"
          class="mr-2"
        />
        <label for="AUCTION">Auction</label>
      </div>
      <div class="mb-2">
        <input
          type="radio"
          name="typeOfSale"
          id="FIXED_PRICE"
          value="FIXED_PRICE"
          v-model="typeOfSale"
          class="mr-2"
        />
        <label for="FIXED_PRICE">Fixed price</label>
      </div>
      <button type="submit" class="mt-4 p-2 bg-blue-500 text-white rounded">
        Filter
      </button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ProductsFilterSidebarComp",
  data() {
    return {
      categories: [],
      selectedCategory: "",
      minPrice: null,
      maxPrice: null,
      typeOfSale: "",
    };
  },
  created() {
    this.fetchCategories();
  },
  methods: {
    async fetchCategories() {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/categories",
        );
        this.categories = response.data; // Adjust based on actual response structure
      } catch (error) {
        console.error("Error fetching categories:", error);
      }
    },
    applyFilter() {
      this.$emit(
        "filter-products",
        this.minPrice,
        this.maxPrice,
        this.selectedCategory,
        this.typeOfSale,
      );
    },
  },
};
</script>
