<template>
    <div class="productpage">
      <h1>Products we're sure you'll like!</h1>
      <div class="grid grid-cols-3" id="products">
        <!-- Render products here -->
        <div v-for="product in products" :key="product.id" class="item flex flex-col ">
          <router-link :to="{ name: 'product', params: { id: product.id }, query: { img: product.imagePath, name: product.name, price: product.price }}">
          <h2 class = "flex justify-center" itemprop="name">{{ product.name }}</h2>
          <br>
          <h3 class = "flex justify-center" itemprop = "price"> ${{  product.price }}</h3>
          <img :src="getImageUrl(product.imagePath)" alt="Product Image" class = "object-contain object-center w-1/2">
        </router-link>
        </div>
      </div>
    </div>
    <div class = "flex flex-row justify-around  paging-buttons">
        <button  @click = "prevPage" :disabled="currentPage === 0" class = "rounded bg-blue-300 ">Previous Page</button>
        <button class = "rounded bg-blue-300" @click = "nextPage" :disabled="currentPage === totalPages">Next Page</button>
    </div>
  </template>
  
  <script>
import { getImageUrl } from './utils'
import ProductPage from './ProductPage.vue'
import axios from 'axios';
import { RouterLink } from 'vue-router';
  
  export default {
    data() {
        return {
            products: [],
            currentPage: 0,
            totalPages: 3,
            getImageUrl
// Initialize products as an empty array
        };
    },
    components: {
      ProductPage
    },
    created() {
        this.retrieveProducts();
    },
    methods: {
        async retrieveSingleProduct(productId) {
            try {
                const response = await axios.get(`http://localhost:8080/api/v1/products/${productId}`);
                console.log(response.data);
                this.$router.push(`/${productId}`);
            }
            catch (error) {
                console.error(error);
            }
        },
        async retrieveProducts() {
            try {
                const response = await axios.get(`http://localhost:8080/api/v1/products?page=${this.currentPage}&size=6`);
                this.products = response.data;
                console.log(response.data); // Ensure the data is logged correctly
            }
            catch (error) {
                console.error(error);
            }
        },
        nextPage() {
            if (this.currentPage < this.totalPages) {
                this.currentPage++;
                this.retrieveProducts();
            }
        },
        prevPage() {
            if (this.currentPage > 0) {
                this.currentPage--;
                this.retrieveProducts();
            }
        },
        logItem(products) {
            console.log(products);
        }
    },
    components: { RouterLink }
};
  </script>
  
  <style scoped>
  .product-image {
    width: 30%;
  }
  .item:hover, .product-image {
    animation-name: initial;
    animation: myanim 0.45s forwards;
  }
  @keyframes myanim {
    from {background-color: white;}
    to {background-color: lightblue;}
  }
  .prod {
    display: grid;
    grid-auto-columns: max-content;
    grid-auto-flow: dense;
    grid-auto-rows: minmax(50px, auto);
    grid-template-columns: repeat(3, 1fr);
  }
  </style>
  