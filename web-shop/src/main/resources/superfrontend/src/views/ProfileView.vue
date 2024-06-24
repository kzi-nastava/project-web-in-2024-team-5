<template>
  <Header />
  <ProfileComp />
  <Footer />
</template>
<script>
import Header from "@/components/Header.vue";
import ProfileComp from "@/components/ViewProfileComp.vue";
import Footer from "@/components/FooterComp.vue";
export default {
  components: {
    Header,
    ProfileComp,
    Footer,
  },
};
</script>
