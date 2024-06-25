<template>
  <Header />
  <PostProduct />
  <Footer />
</template>
<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/FooterComp.vue";
import PostProduct from "@/components/PostProductComp.vue";
export default {
  components: {
    Header,
    PostProduct,
    Footer,
  },
};
</script>
