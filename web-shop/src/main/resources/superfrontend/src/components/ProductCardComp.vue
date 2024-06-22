<template>
  <div class="border p-4 rounded-lg shadow">
    <img :src="productImage" alt="Product Image" class="mb-4" />
    <h3 class="text-lg font-bold mb-2">{{ product.name }}</h3>
    <p class="text-gray-700 mb-4">{{ product.price }} rsd</p>
    <button class="text-blue-500">more...</button>
  </div>
</template>

<script>
export default {
  name: 'ProductCardComp',
  props: {
    product: {
      type: Object,
      required: true
    }
  }
};
</script>

<style scoped>
/* Add any specific styles for your product card here */
</style>
