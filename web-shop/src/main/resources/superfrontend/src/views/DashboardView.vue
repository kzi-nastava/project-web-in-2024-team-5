<template>
  <Header></Header>
  <DashboardSection />
  <FooterComp />
</template>

<script>
import Header from "@/components/Header.vue";
import DashboardSection from "@/components/DashboardSectionComp.vue";
import FooterComp from "@/components/FooterComp.vue";

export default {
  components: {
    Header,
    DashboardSection,
    FooterComp,
  },
};
</script>
