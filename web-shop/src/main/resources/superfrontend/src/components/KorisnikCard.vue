<template>
    <div class="p-3">
        <router-link :to="{
            name: 'profile',
            params: { id: this.korisnik.id },
        }">

            <p class="text-2xl">{{ korisnik.name }}</p>
            <p>{{ korisnik.lastname }}</p>
            <p>{{ korisnik.role }}</p>
        </router-link>
    </div>
</template>

<script>
export default {
    name: "KorisnikCard",
    props: {
        korisnik: {
            type: Object,
        }
    },
}
</script>