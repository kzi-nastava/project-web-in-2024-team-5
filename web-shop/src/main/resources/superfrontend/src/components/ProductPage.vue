<script>
import { getImageUrl } from './utils';
export default {
    computed: {
        productId() {
            return this.$route.params.id;
        },
        productName() {
            return this.$route.query.name;
        },
        productPrice() {
            return this.$route.query.price;
        },
        productImage() {
            return this.$route.params.img;
        }
    },
    methods: {
        getImageUrl
    },
};
</script>

<template>
    <div class= "flex flex-col justify-center items-center">
      <h1>Product details:</h1>
      <h2>ID: {{ productId}}</h2>
      <h2>Name: {{ productName }}</h2>
      <h2>Price: {{ productPrice }}</h2>
      <img :src = "getImageUrl(productImage)" class = "object-contain object-center w-1/5">
      <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full">
        Buy product!</button>
    </div>
  </template>