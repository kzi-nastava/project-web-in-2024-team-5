<template>
  <div class="container w-[1200px] py-8 mx-auto">
    <div class="flex flex-row items-center justify-between">
      <div
        class="flex item-center justify-center bg-white drop-shadow-lg rounded-2xl h-[550px] w-[550px]"
      >
        <img
          :src="getImageUrl(this.product.imagePath)"
          class="object-contain object-center w-1/2"
        />
      </div>
      <div class="flex flex-col h-[550px]">
        <div
          v-if="this.product.typeOfSale == 'FIXED_PRICE'"
          class="flex flex-col w-[500px] h-[380px] mb-auto font-[Open_Sans]"
        >
          <div class="flex-grow">
            <h2 class="text-[40px]">{{ this.product.name }}</h2>
            <p class="text-xl break-words">{{ this.product.description }}</p>
          </div>
          <h2 class="mb-6 text-2xl text-[#B84E15]">
            Price: {{ this.product.price }}
          </h2>
          <button
            class="bg-blue-500 place-self-end hover:bg-blue-700 w-full text-white font-bold py-2 px-4 rounded-full"
            @click="buyProduct"
          >
            Kupi proizvod
          </button>
        </div>
        <div
          v-else
          class="flex flex-col w-[500px] h-[380px] mb-auto font-[Open_Sans]"
        >
          <div class="flex-grow">
            <h2 class="text-[40px]">{{ this.product.name }}</h2>
            <p class="text-xl break-words">{{ this.product.description }}</p>

            <h2 class="mb-6 text-xl">Pocetna cena: {{ this.product.price }}</h2>
          </div>
          <h2 class="mb-6 text-2xl text-[#B84E15]">
            Poslednja ponuda: {{ this.product.price }}
          </h2>
          <div class = "flex flex-row items-center mb-6">
          <input class = "w-2/3" v-model = "offer" type = "number" placeholder= "Unesi ponudu"></input>
          <button
            class="bg-blue-500 place-self-end hover:bg-blue-700 w-1/3 text-white font-bold py-2 px-4 rounded-full"
            @click="addOffer"
          >
              
            Daj ponudu
          </button>
          </div>
        </div>

        <div
          class="bg-white rounded-xl flex flex-col justify-center items-center drop-shadow-lg h-[150px]"
        >
          <h2 class="text-2xl text-center">
            {{ this.seller.name + " " + this.seller.lastname }}
          </h2>
          <h2 class="text-xl text-center">{{ "Prosecna ocena: " }}</h2>
          <h2 class="text-lg text-center">
            {{ "Pogledaj sve recenzije... " }}
          </h2>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import { getImageUrl } from "./utils";
export default {
  data() {
    return {
      product: {},
      seller: {},
      offer: {},
    };
  },
  computed: {
    productId() {
      return this.$route.params.id;
    },
  },
  async mounted() {
    await this.fetchProduct();
    this.fetchSeller();
    console.log(this.seller);
  },
  methods: {
    getImageUrl,
    async buyProduct() {
      try {
        const response = await axios.post(
          `http://localhost:8080/api/v1/products/${this.productId}/purchase`,
        );
        console.log(response.data);
      } catch (error) {}
    },
    async addOffer(amount) {
    try {
        const response = await axios.post("http://localhost:8080/api/v1/offers", {
          price: this.offer,
          productId: Number(this.productId),
        });

    }
      catch(error) {

      }

    },
    async fetchProduct() {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/products/${this.productId}`,
        );

        this.product = response.data;
      } catch (error) {}
    },
    async fetchSeller() {
      try {
        console.log(this.product.sellerId);
        const response = await axios.get(
          `http://localhost:8080/api/v1/users/${this.product.sellerId}`,
        );
        this.seller = response.data;
        console.log(this.seller);
      } catch (error) {}
    },
  },
};
</script>
