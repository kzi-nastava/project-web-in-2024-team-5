<template>
  <Header />
  <EditProductComp />
  <Footer />
</template>
<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/FooterComp.vue";
import EditProductComp from "@/components/EditProductComp.vue";
export default {
  components: {
    Header,
    EditProductComp,
    Footer,
  },
};
</script>
