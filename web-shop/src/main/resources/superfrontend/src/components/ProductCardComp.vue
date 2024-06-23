<template>
  <div class="border p-4 rounded-lg shadow">
    <router-link
      :to="{
        name: 'product',
        params: { id: product.id },
      }"
    >
      <img :src="productImage" alt="Product Image" class="mb-4" />
      <h3 class="text-lg font-bold mb-2">{{ product.name }}</h3>
      <p class="text-gray-700 mb-4">{{ product.price }} rsd</p>
      <p class="text-blue-500">more...</p>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "ProductCardComp",
  props: {
    product: {
      type: Object,
      required: true,
    },
  },
};
</script>
