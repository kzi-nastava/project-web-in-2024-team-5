<template>
  <section class="py-12">
    <div class="container mx-auto w-[1200px]">
      <div class="flex justify-between w-full">
        <ProductsFilterSidebarComp @filter-products="filterProducts" />
        <ProductGridComp :filters="filters" />
      </div>
    </div>
  </section>
</template>

<script>
import ProductGridComp from "@/components/ProductsGridComp.vue";
import ProductsFilterSidebarComp from "@/components/ProductsFilterSidebarComp.vue";

export default {
  name: "ProductsGridSection",
  components: {
    ProductGridComp,
    ProductsFilterSidebarComp,
  },
  props: {
    filters: {
      type: Object,
    },
  },
  methods: {
    filterProducts(minPrice, maxPrice, selectedCategory, typeOfSale) {
      this.$emit("update-filters", {
        minPrice,
        maxPrice,
        category: selectedCategory,
        typeOfSale,
      });
    },
  },
};
</script>
