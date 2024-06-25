<template>
    <section class="py-12">
        <div class="container w-[1200px] mx-auto">
            <KorisnikCardGrid />
        </div>
    </section>
</template>

<script>
    import KorisnikCardGrid from "@/components/KorisnikCardGrid.vue"
    export default {
        name: "KorisniciSekcija",
        components: {
            KorisnikCardGrid,
        }
    }
</script>