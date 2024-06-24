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
      <div v-if="sold != true" class="flex flex-col h-[550px]">
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
<RouterLink
              :to="{
                name: 'editproduct',
                params: {
                  id: product.id,
                },
              }"
              v-if="this.user.id == this.seller.id"
              class="text-center bg-blue-500 place-self-end hover:bg-blue-700 w-full text-white font-bold py-2 px-4 rounded-full"
             >
              Izmeni proizvod
            </RouterLink>

          <button v-if = "this.user.id != this.seller.id"
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
          <input v-if ="this.user.id != this.seller.id" class = "w-2/3" v-model = "offer" type = "number" placeholder= "Unesi ponudu"></input>
          <button v-if ="this.user.id != this.seller.id"
            class="bg-blue-500 place-self-end hover:bg-blue-700 w-1/3 text-white font-bold py-2 px-4 rounded-full"
            @click="addOffer"
          >
              
            Daj ponudu
          </button>
          <button v-else
            class="bg-red-500 place-self-end hover:bg-red-700 w-full text-white font-bold py-2 px-4 rounded-full"
            @click="endAuction"
          >
              
            Zavrsi aukciju
          </button>

          </div>
        </div>

        <div
          class="bg-white rounded-xl flex flex-col justify-center items-center drop-shadow-lg h-[150px]"
        >
          <RouterLink :to="{
              name: 'profile',
              params: { id: seller.id },
              }">
          <h2 class="text-2xl text-center">
            {{ this.seller.name + " " + this.seller.lastname }}
          </h2>
          </RouterLink>
          <h2 class="text-xl text-center">{{ this.average }}</h2>
          <h2 class="text-lg text-center">
            {{ "Pogledaj sve recenzije... " }}
          </h2>
        </div>

      </div>
      <div v-else class = "h-[550px] w-1/2 flex flex-col mb-auto">
<div class = "flex-grow justify-self-start">
            <h2 class="text-[40px]">{{ this.product.name }}</h2>
            <p class="text-xl break-words">{{ this.product.description }}</p>

          </div>
<div
          class=" bg-white  rounded-xl flex flex-row justify-center items-center drop-shadow-lg h-[150px]"
        >
          <div class = "flex flex-col">

          <RouterLink :to="{
              name: 'profile',
              params: { id: seller.id },
              }">
          <h2 class="text-2xl text-center">
            {{ this.seller.name + " " + this.seller.lastname }}
          </h2>
            </RouterLink>
          <h2 class="text-xl text-center">{{ this.average }}</h2>
          <h2 class="text-lg text-center">
            {{ "Pogledaj sve recenzije... " }}
          </h2>
          </div>
        </div>
        <div class = "flex flex-row m-2 justify-between">
          <RouterLink :to = "{
            name: 'reviews',
            params: {
              id: seller.id,
            },

          }" class = "w-1/2 p-3 drop-shadow-lg text-center rounded-2xl text-white font-bold text-2xl bg-blue-500">Oceni korisnika</RouterLink>
          <RouterLink :to = "{
            name: 'reports',
            params: {
              id: seller.id,
            },

          }" class = "w-1/2 p-3 drop-shadow-lg text-center rounded-2xl text-white font-bold text-2xl bg-red-500">Prijavi korisnika</RouterLink>
        </div>

      </div>

<Teleport to="body">
        <modal :show="showauc" @close="showauc = false">
          <template #header>
            <h3>Uspešno ste postavili svoju ponudu!</h3>
          </template>
          <template #footer >
            <button class = "bg-red-600 rounded-2xl drop-shadow-lg  px-3 py-1
              text-lg text-white" @click="showauc = false">Zatvori</button>
          </template>
        </modal>
      </Teleport>
    <Teleport to="body">
        <modal :show="showmsg" @close="showmsg = false">
          <template #header>
            <h3>Hvala na kupovini!</h3>
          </template>
          <template #footer >
            <button class = "bg-red-600 rounded-2xl drop-shadow-lg  px-3 py-1
              text-lg text-white" @click="showmsg = false">Zatvori</button>
          </template>
        </modal>
      </Teleport>
<Teleport to="body">
        <modal :show="msgauction" @close="msgauction= false">
          <template #header>
            <h3>Uspešno ste završili aukciju!</h3>
          </template>
          <template #footer >
            <button class = "bg-red-600 rounded-2xl drop-shadow-lg  px-3 py-1
              text-lg text-white" @click="msgauction = false">Zatvori</button>
          </template>
        </modal>
      </Teleport>

    </div>
  </div>
</template>
<script>
import axios from "axios";
import Modal from "@/components/SuccessComp.vue";
import { getImageUrl, fetchSelf } from "./utils";
export default {
  data() {
    return {
      user: {},
      product: {},
      seller: {},
      offer: {},
      average: {},
      msgbought: false,
      msgauction: false,
      sold: false,
      showmsg: false,
           openedReport: false,
      showauc: false,

    };
  },
  computed: {
    productId() {
      return this.$route.params.id;
    },
  },
  async mounted() {
    await this.fetchProduct();
    await this.fetchSeller();
    await this.getAverage();
    this.user = await this.fetchSelf();
    console.log(this.user);
    console.log(this.seller);
  },
  components: {
    Modal,

  },
  methods: {
    getImageUrl,
    fetchSelf,
    async getAverage() {
      try{
        const response = await axios.get(`http://localhost:8080/api/v1/reviews/average?reviewedUserId=${this.product.sellerId}`)
        this.average = response.data;
        console.log(this.average);
      }catch(error) {

      }
    },
    async endAuction() {
      try {
        const response = await axios.post(
          `http://localhost:8080/api/v1/products/${this.productId}/end-auction`,
        );
        if(response.status == 200) {
          this.msgauction = true;
        }
        console.log(response.data);
      } catch (error) {}
    },
    async buyProduct() {
      try {
        const response = await axios.post(
          `http://localhost:8080/api/v1/products/${this.productId}/purchase`,
        );
        if(response.status === 200 ) {
        this.product.isSold = true;
          this.sold = true;
          this.showmsg = true;
          this.$forceUpdate();
          console.log(this.productId);
        }
        console.log(response.data);
        
      } catch (error) {}
    },
    async addOffer(amount) {
    try {
        const response = await axios.post("http://localhost:8080/api/v1/offers", {
          price: this.offer,
          productId: Number(this.productId),
        });
        this.showauc = true;

          this.$forceUpdate();

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
        console.log(this.product)
      } catch (error) {}
    },
    async fetchSeller() {
  try {

        if(this.product.sellerId == undefined) {
          this.sold = true;
        this.product = this.product.productDto;
        }
        const response = await axios.get(`
          http://localhost:8080/api/v1/users/${this.product.sellerId}`
        );

        const seller = {...response.data, id: this.product.sellerId};
        this.seller = seller;

      } catch (error) {}
    },
    closeModal() {
      this.showmsg = false;
      this.sold = true; // Update the state to reflect the sale
    },
  },
};
</script>
