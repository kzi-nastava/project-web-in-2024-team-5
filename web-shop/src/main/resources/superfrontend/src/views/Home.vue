<template>
    <Header/>
    <div class = "ml-12"><Sidebar/></div>
</template>

<script>
import HomePage from '@/components/HomePage.vue';
import Sidebar from '@/components/Sidebar.vue';
import Header from '@/components/Header.vue'
import Products from '@/components/ProductsPage.vue'

    export default {
    components: {Sidebar, Header, HomePage}
}
</script>

<style>

</style>