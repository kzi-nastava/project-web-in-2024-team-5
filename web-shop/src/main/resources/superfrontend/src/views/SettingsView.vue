<template>
  <Header />
  <SettingsComp />
  <Footer />
</template>
<script>
import Header from "@/components/Header.vue";
import SettingsComp from "@/components/SettingsComp.vue";
import Footer from "@/components/FooterComp.vue";
export default {
  components: {
    Header,
    SettingsComp,
    Footer,
  },
};
</script>
