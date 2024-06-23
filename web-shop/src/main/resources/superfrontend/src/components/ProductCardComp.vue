<template>
  <div class="border p-4 rounded-lg shadow">
    <router-link
      :to="{
        name: 'product',
        params: { id: product.id },
      }"
    >
      <img
        :src="getImageUrl(product.imagePath)"
        alt="Product Image"
        class="object-contain object-center w-1/2"
      />
      <h3 class="text-lg font-bold mb-2">{{ product.name }}</h3>
      <p class="text-gray-700 mb-4">{{ product.price }} rsd</p>
      <p class="text-blue-500">more...</p>
    </router-link>
  </div>
</template>

<script>
import { getImageUrl } from "./utils";
export default {
  data() {
    return {
      getImageUrl,
    };
  },
  name: "ProductCardComp",
  props: {
    product: {
      type: Object,
      required: true,
    },
  },
};
</script>
