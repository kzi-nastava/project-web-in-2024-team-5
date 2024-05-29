<script>
import { getImageUrl } from './utils';
export default {
    methods: {
        getImageUrl
    },
};
</script>

<template>
    <div>
      <h1>Product details:</h1>
      <h2>ID: {{ $route.params.id }}</h2>
      <h2>Name: {{ $route.query.name }}</h2>
      <h2>Price: {{ $route.query.price }}</h2>
      <img :src = "getImageUrl($route.query.img)">

      <!-- Fetch product details using the received props -->
    </div>
  </template>