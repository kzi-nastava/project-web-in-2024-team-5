<template>
  <Header />
  <ReportFormComp />
  <Footer />
</template>
<script>
import Header from "@/components/Header.vue";
import ReportFormComp from "@/components/ReportFormComp.vue";
import Footer from "@/components/FooterComp.vue";
export default {
  components: {
    Header,
    ReportFormComp,
    Footer,
  },
};
</script>
