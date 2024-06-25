<template >
  <Header></Header>
  <DashboardSection />
  <FooterComp />
</template>

<script>
import Header from "@/components/Header.vue";
import DashboardSection from "@/components/DashboardSectionComp.vue";
import FooterComp from "@/components/FooterComp.vue";
import { fetchSelf } from "../components/utils.js";

export default {
  components: {
    Header,
    DashboardSection,
    FooterComp,
  },
  data() {
    return {
      user: {
        type: Object,
      },
    };
  },
  async mounted() {
    const response = await fetchSelf();
    this.user = response;
    if(this.user.role !== "admin") {
      this.$router.push("/")
    }
  },
};
</script>
