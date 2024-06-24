<template>
    <div class="w-full">
        <div v-if="loading" class="text-center">Loading reviews...</div>
        <div v-else class="grid grid-cols-4 gap-4">
            <ReviewCard v-for="review in reviews" :key="review.id" :review="review" @review-updated="fetchReviews" />
        </div>
    </div>
</template>

<script>

import ReviewCard from "@/components/ReviewCardComp.vue"
import axios from 'axios';

export default {
    name: "ReviewsGrid",
    components: {
        ReviewCard,
    },
    data() {
    return {
      reviews: [],
    };
  },
  mounted() {
    this.fetchReviews();
  },
  methods: {
    async fetchReviews() {
      try {
        const res = await axios.get("http://localhost:8080/api/v1/reviews");
        this.reviews = res.data;
      } catch (error) {
        console.error("Error fetching reviews:", error);
      }
    },
  },
}

</script>