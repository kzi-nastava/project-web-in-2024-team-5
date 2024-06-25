<template>
  <Header />
  <ReviewFormComp />
  <Footer />
</template>
<script>
import Header from "@/components/Header.vue";
import ReviewFormComp from "@/components/ReviewFormComp.vue";
import Footer from "@/components/FooterComp.vue";
export default {
  components: {
    Header,
    ReviewFormComp,
    Footer,
  },
};
</script>
