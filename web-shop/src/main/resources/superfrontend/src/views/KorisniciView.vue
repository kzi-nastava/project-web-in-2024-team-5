<template>
    <Header />
    <KorisniciSekcija />
    <FooterComp />
</template>

<script>
import Header from "@/components/Header.vue"
import FooterComp from "@/components/FooterComp.vue";
import KorisniciSekcija from "@/components/KorisniciSekcija.vue";

export default {
    components: {
        Header,
        KorisniciSekcija,
        FooterComp
    }
}
</script>