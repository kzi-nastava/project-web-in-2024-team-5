<template>
  <div class="container w-[1200px] mx-auto">
    <div class="flex flex-col justify-start text-[Open_Sans]">
      <h2 class="text-center text-4xl">Moj profil</h2>

      <div class="m-8 flex flex-row justify-center items-center">
        <div class="bg-white w-1/4 drop-shadow-lg rounded-2xl">
          <img src="../../public/assets/person.svg" />
        </div>
        <div class="flex flex-col m-8">
          <h2 class="text-4xl">{{ user.name + " " + user.lastname }}</h2>
          <h2 class="text-4xl">{{ user.email }}</h2>
          <h2 class="text-4xl">{{ user.phoneNumber }}</h2>
          <RouterLink to="/settings"
            ><h2 class="text-[#004E9D] text-2xl">Izmeni podatke</h2></RouterLink
          >
        </div>
      </div>
      <div v-if="!admin" class="flex flex-col">
        <h2 v-if="this.kupac" class="py-8 text-4xl">
          Proizvodi koje sam kupio
        </h2>
        <h2 v-if="this.prodavac" class="py-8 text-4xl">
          Proizvodi koje prodajem
        </h2>

        <div class="grid grid-cols-4">
          <div v-for="product in products" :key="product.id">
            <ProductCard :product="product" />
          </div>
        </div>
        <h2 class="py-8 text-4xl">Recenzije koje sam dobio</h2>
        <div class="grid grid-cols-3">
          <div v-for="review in receivedReviews" :key="review.id">
            <div
              class="flex flex-row h-2/3 w-fit justify-start bg-white drop-shadow-lg rounded-2xl"
            >
              <img src="../../public/assets/person.svg" class="w-1/2" />
              <div class="flex flex-col">
                <p class="text-2xl">
                  {{
                    review.reviewer.name + " " + review.reviewer.lastname + " "
                  }}
                </p>
                <p class="text-2xl">
                  {{ "Ocena: " + review.score }}
                </p>
                <p>{{ review.comment }}</p>
              </div>
            </div>
          </div>
        </div>
        <h2 class="py-8 text-4xl">Recenzije koje sam dao</h2>
        <div class="grid grid-cols-3">
          <div v-for="review in postedReviews" :key="review.id">
            <div
              class="flex flex-row h-2/3 w-fit justify-start bg-white drop-shadow-lg rounded-2xl"
            >
              <img src="../../public/assets/person.svg" class="w-1/2" />
              <div class="flex flex-col">
                <p class="text-2xl">
                  {{
                    review.reviewer.name + " " + review.reviewer.lastname + " "
                  }}
                </p>
                <p class="text-2xl">
                  {{ "Ocena: " + review.score }}
                </p>
                <p>{{ review.comment }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  fetchSelf,
  fetchUser,
  retrieveMyProducts,
  retrieveMyReceivedReviews,
  retrieveMyPostedReviews,
  getImageUrl,
} from "./utils.js";
import ProductCard from "@/components/ProductCardComp.vue";
import axios from "axios";
export default {
  data() {
    return {
      user: {},
      reviewers: [],
      postedReviews: [],
      receivedReviews: [],
      products: [],
      kupac: false,
      prodavac: false,
      admin: false,
      getImageUrl,
    };
  },
  async created() {
    this.user = await fetchSelf();
    if (this.user.role === "buyer") {
      this.kupac = true;
      this.prodavac = false;
      this.admin = false;
    } else if (this.user.role === "seller") {
      this.kupac = false;
      this.prodavac = true;
      this.admin = false;
    } else if (this.user.role === "admin") {
      this.kupac = false;
      this.prodavac = false;
      this.admin = true;
    }
    await this.fetchMyProducts();
    await this.fetchUsersReviewers();
    await this.fetchReceivedUsers();
  },
  methods: {
    async fetchUsersReviewers() {
      const resp = await this.fetchReceivedReviews();
      const reviews = resp;
      const reviewerPromises = reviews.map(async (review) => {
        const reviewer = await fetchUser(review.reviewingUserId);
        return { ...review, reviewer };
      });
      this.receivedReviews = await Promise.all(reviewerPromises);
    },
    async fetchReceivedUsers() {
      const resp = await this.fetchPostedReviews();
      const reviews = resp;
      const reviewerPromises = reviews.map(async (review) => {
        const reviewer = await fetchUser(review.reviewedUserId);
        return { ...review, reviewer };
      });
      this.postedReviews = await Promise.all(reviewerPromises);
      console.log(this.postedReviews);
    },
    async fetchReceivedReviews() {
      const response = await retrieveMyReceivedReviews();
      return response.data;
    },
    async fetchPostedReviews() {
      const response = await retrieveMyPostedReviews();
      return response.data;
    },
    async fetchMyProducts() {
      const response = await retrieveMyProducts(this.products, this.user.id);
      this.products = response;
    },
  },
  components: {
    ProductCard,
  },
};
</script>
