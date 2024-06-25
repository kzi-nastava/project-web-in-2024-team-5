<template>
  <div class="container w-[1200px] mx-auto">
    <div class="flex flex-col justify-start text-[Open_Sans]">
      <div class="m-8 flex flex-row justify-center items-center">
        <div class="bg-white w-1/4 drop-shadow-lg rounded-2xl">
          <img src="../../public/assets/person.svg" />
        </div>
        <div class="flex flex-col m-8">
          <h2 class="text-4xl">{{ profile.name + " " + profile.lastname }}</h2>
          <h2 class="text-4xl">{{ profile.email }}</h2>
          <h2 class="text-4xl">{{ profile.phoneNumber }}</h2>
        </div>
      </div>
      <div v-if="!admin" class="flex flex-col">
        <h2 v-if="this.kupac" class="py-8 text-4xl">
          Proizvodi koje je ovaj korisnik kupio
        </h2>
        <h2 v-if="this.prodavac" class="py-8 text-4xl">
          Proizvodi koje ovaj korisnik prodaje
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
        <div v-if="profile.role == 'seller'">
          <h2 v-if="givenReview" class="text-4xl py-8">
            Recenzije koje je ovaj prodavac postavio
          </h2>
          <h2 v-else class="py-8 text-4xl">
            Niste ocenili ovog prodavca, ne možete videti recenzije koje je on
            postavio.
          </h2>
        </div>
        <div v-if="profile.role == 'buyer'">
          <h2 v-if="givenReview" class="text-4xl py-8">
            Recenzije koje je ovaj kupac postavio
          </h2>
          <h2 v-else class="py-8 text-4xl">
            Niste ocenili ovog kupca, ne možete videti recenzije koje je on
            postavio.
          </h2>
        </div>

        <div v-if="givenReview" class="grid grid-cols-3">
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
  retrieveReceivedReviews,
  retrievePostedReviews,
  getImageUrl,
} from "./utils.js";
import ProductCard from "@/components/ProductCardComp.vue";
import axios from "axios";
export default {
  data() {
    return {
      user: {},
      profile: {},
      reviewers: [],
      postedReviews: [],
      receivedReviews: [],
      products: [],
      givenReview: false,
      kupac: false,
      prodavac: false,
      admin: false,
      getImageUrl,
    };
  },
  computed: {
    profileId() {
      return this.$route.params.id;
    },
  },
  async created() {
    this.profile = await fetchUser(this.profileId);
    this.user = await fetchSelf();
    if (this.profile.role === "buyer") {
      this.kupac = true;
      this.prodavac = false;
      this.admin = false;
    } else if (this.profile.role === "seller") {
      this.kupac = false;
      this.prodavac = true;
      this.admin = false;
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
      const response = await retrieveReceivedReviews(this.profileId);
      console.log(response);
      return response.data;
    },
    async fetchPostedReviews() {
      const response = await retrievePostedReviews(this.profileId);
      console.log(response);
      if (response.data.length > 0) {
        this.givenReview = true;
      }
      return response.data;
    },
    async fetchMyProducts() {
      const response = await retrieveMyProducts(this.products, this.profileId);
      this.products = response;
      console.log(this.products);
    },
  },
  components: {
    ProductCard,
  },
};
</script>
