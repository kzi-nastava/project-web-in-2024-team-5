<template>
  <section
    class="ml-0 mr-0 pt-3 text-center relative text-white font-['Open_Sans']"
  >
    <div class="brightness-[.2]">
      <img src="/src/assets/bkgr.jpeg" />
    </div>
    <div class="container w-[1200px]">
      <h1 class="absolute top-[83px] text-5xl left-[18.75%] font-extrabold">
        Big Hero Title
      </h1>
      <p class="absolute top-[183px] text-xl left-[18.75%] w-96 text-left">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
        veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
        commodo consequat.
      </p>
      <div class="flex flex-row justify-center items-center gap-10">
        <RouterLink to="/login">
          <button
            class="absolute top-[383px] left-[18.75%] drop-shadow-xl rounded-xl w-[105px] h-[41px] bg-[#B84E15]"
          >
            Register
          </button>
        </RouterLink>
        <RouterLink to="/register">
          <button
            class="absolute top-[390px] left-[25%] drop-shadow-xl rounded-xl w-[76px] h-[27px] bg-white text-s text-black"
          >
            Login
          </button>
        </RouterLink>
      </div>
    </div>
  </section>
  <div
    class="container w-[1200px] flex flex-col ml-auto mr-auto pt-4 bg-[#FBFBF9]"
  >
    <div class="font-[Open_Sans] text-[#011839]">
      <h2 class="font-bold text-4xl">Izdvojeni proizvodi</h2>
      <br />
      <p class="text-xl">Ove nedelje izdvajamo ove proizvode</p>
    </div>
    <div class="container">
      <div class="grid grid-cols-4" id="products">
        <div
          v-for="product in products"
          :key="product.id"
          class="item flex flex-col"
        >
          <router-link
            :to="{
              name: 'product',
              params: { id: product.id },
              query: {
                img: product.imagePath,
                name: product.name,
                price: product.price,
              },
            }"
          >
            <div
              class="flex flex-col justify-center items-center bg-white h-[295px] w-[265px] rounded-2xl drop-shadow-lg font-[Open_Sans]"
            >
              <img
                :src="getImageUrl(product.imagePath)"
                alt="Product Image"
                class="bg-transparent pb-2 self-center w-1/2"
              />

              <h2 class="font-semibold text-lg" itemprop="name">
                {{ product.name }}
              </h2>
              <br />
              <h3
                class="absolute bottom-4 left-[10%] text-orange-800"
                itemprop="price"
              >
                ${{ product.price }}
              </h3>
            </div>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped></style>

<script>
import axios from "axios";
import { getImageUrl } from "./utils.js";
import { retrieveProducts } from "./utils.js";
export default {
  data() {
    return {
      products: [],
      getImageUrl,
      retrieveProducts,
    };
  },
  methods: {
    async fetchProducts() {
      const response = await retrieveProducts(this.products, 1, 4);
      this.products = response;
    },
  },
  created() {
    this.fetchProducts();
  },
};
</script>
