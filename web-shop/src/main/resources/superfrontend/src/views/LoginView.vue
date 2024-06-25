<template>
    <Header />
    <LoginPage />
</template>

<script>
import Header from "@/components/Header.vue"
import LoginPage from "@/components/LoginPage.vue"

export default {
    components: {
        Header, 
        LoginPage,
    }
}
</script>