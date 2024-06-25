<template>
    <div class="w-full">
        <div v-if="loading" class="text-center">Loading products...</div>
        <div v-else class="grid grid-cols-3 gap-4">
            <KorisnikCard v-for="korisnik in korisnici" :key="korisnik.id" :korisnik="korisnik" />
        </div>
    </div>
</template>

<script>
import axios from "axios"
import KorisnikCard from "@/components/KorisnikCard.vue"
import { fetchSelf } from "./utils";

export default {
    name: "KorisnikCardGrid",
    components: {
        KorisnikCard,
    },
    data() {
        return {
            korisnici: []
        }
    },
    async mounted() {
        const meRes = await fetchSelf()
        if (!meRes) {
            alert("Morate biti ulogovani da bi ste pristupili ovoj ruti")
            this.$router.push('/')
        }
        const res = await axios.get("http://localhost:8080/api/v1/users")
        this.korisnici = res.data
    }
}
</script>