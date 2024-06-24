<template>
  <section class="py-16">
    <div class="container w-[1200px] mx-auto">
      <h1 class="text-[#011839] text-6xl mb-14">Admin dashboard</h1>
      <h2 class="text-[#011839] text-4xl mb-14">Recenzije</h2>
      <div v-if="loading" class="text-center">Ucitavam recenzije...</div>
      <div v-else class="mb-10">
        <ReviewsGrid />
      </div>
      <h2 class="text-[#011839] text-4xl mb-14">Prijave</h2>
      <div v-if="loading" class="text-center">Ucitavam recenzije...</div>
      <div v-else class="mb-10">
        <ReportsGrid />
      </div>
    </div>
  </section>
</template>

<script>

import ReviewsGrid from "@/components/ReviewCardGrid.vue"
import ReportsGrid from "@/components/ReportCardGrid.vue"

export default {
  name: "DashboardSection",
  components: {
    ReviewsGrid,
    ReportsGrid 
  }
};
</script>
