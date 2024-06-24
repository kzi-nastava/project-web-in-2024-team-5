<template>
    <div class = "container w-[1200px] mx-auto">
    <div class = "flex flex-col font-[Open_Sans] my-4 text-2xl">
            <h2 class = "font-semibold text-3xl">Popunite formu za prijavljivanje korisnika.</h2>
            <div class = "bg-white rounded-2xl shadow-xl">
            <form @submit.prevent="postreport" class = "flex flex-col p-2 space-y-4">
    <label for = "comment">Razlog prijave</label>
    <input v-model = "report.reason" form = "text" placeholder="Unesi razlog" id = "comment"></input>

                    <input class ="justify-self-end drop-shadow-lg bg-red-600 hover:bg-red-800 text-white h-full rounded-2xl " type = "submit" value = "Postavi prijavu"></input> 

            </form>
            </div>
    </div>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    data() {
        return {
            report: {},
        }
    },
    computed: {
        userId() {
            return this.$route.params.id;
        }
    },
    methods: {
    async postreport() {
            try {
        const response = await axios.post(`http://localhost:8080/api/v1/reports/${this.userId}`, this.report);
                console.log(response);
            }
            catch(error) {
                
            }
    },
    },
};
</script>
