<template>
  <section
    class="ml-0 mr-0 pt-3 text-center relative text-white font-['Open_Sans']"
  >
    <div class="brightness-[.2]">
      <img src="/src/assets/bkgr.jpeg" />
    </div>
    <div class="container w-[1200px]">
      <h1 class="absolute top-[83px] text-5xl left-[18.75%] font-extrabold">
        Super web shop
      </h1>
      <p class="absolute top-[183px] text-xl left-[18.75%] w-96 text-left">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
        veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
        commodo consequat.
      </p>
      <div class="flex flex-row justify-center items-center gap-10">
        <RouterLink to="/register">
          <button
            class="absolute top-[383px] left-[18.75%] drop-shadow-xl rounded-xl w-[105px] h-[41px] bg-[#B84E15]"
          >
            Register
          </button>
        </RouterLink>
        <RouterLink to="/login">
          <button
            class="absolute top-[390px] left-[25%] drop-shadow-xl rounded-xl w-[76px] h-[27px] bg-white text-s text-black"
          >
            Login
          </button>
        </RouterLink>
      </div>
    </div>
  </section>
  <section>
    <div
      class="container w-[1200px] flex flex-col ml-auto mr-auto pt-[80px] pb-[80px] bg-[#FBFBF9]"
    >
      <div class="font-[Open_Sans] text-[#011839]">
        <h2 class="font-bold text-4xl">Izdvojeni proizvodi</h2>
        <br />
        <p class="text-xl">Ove nedelje izdvajamo ove proizvode</p>
        <br />
      </div>
      <div class="flex flex-row">
        <div v-for="product in products" :key="product.id" class="item w-1/3">
          <router-link
            :to="{
              name: 'product',
              params: { id: product.id },
            }"
          >
            <ProductCard :product="product" />
          </router-link>
        </div>
      </div>

      <RouterLink class="self-end py-2" to="/products"
        ><p class="text-[#004E9D] text-lg">
          Pogledajte sve proizvode
        </p></RouterLink
      >
    </div>
  </section>
  <section class="bg-[#B84E15] h-[400px]">
    <div
      class="container flex flex-row items-center mx-auto w-[1200px] h-[400px] font-[Open_Sans]"
    >
      <div>
        <h2 class="text-5xl text-white font-bold">Preporučene kategorije</h2>
        <br />
        <div class="text-2xl text-white max-w-[350px]">
          <p>Imamo mnogo različitih proizvoda.</p>
          <p>Pogledajte neke od proizvoda iz izdvojenih kategorija.</p>
        </div>
      </div>
      <div class="flex flex-col gap-8 pl-64">
        <div
          class="flex flex-row justify-start gap-2 p-3 bg-white w-[276px] items-center h-[60px] rounded-xl drop-shadow-lg text-xl"
        >
          <img src="/public/assets/krevet.svg" />
          Nameštaj
        </div>
        <div
          class="flex flex-row justify-start gap-2 p-3 bg-white w-[276px] items-center h-[60px] rounded-xl drop-shadow-lg text-xl"
        >
          <img src="/public/assets/knjiga.svg" />

          Knjige
        </div>
        <div
          class="flex flex-row justify-start gap-2 p-3 bg-white w-[276px] items-center h-[60px] rounded-xl drop-shadow-lg text-xl"
        >
          <img src="/public/assets/monitor.svg" />

          Tehnologija
        </div>
      </div>
    </div>
  </section>
</template>
<style scoped></style>

<script>
import axios from "axios";
import { fetchSelf, getImageUrl } from "./utils.js";
import { retrieveProducts } from "./utils.js";
import ProductCard from "@/components/ProductCardComp.vue";
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
  async created() {
    this.fetchProducts();
    const res = await fetchSelf()
  },
  components: {
    ProductCard,
  },
};
</script>
