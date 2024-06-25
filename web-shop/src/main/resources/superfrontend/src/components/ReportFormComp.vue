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
<Teleport to="body">
        <modal :show="showerror" @close="showerror= false">
          <template #header>
            <h3>Niste uspešno prijavili ovog korisnika!</h3>
          </template>
          <template #footer >
            <button class = "bg-red-600 rounded-2xl drop-shadow-lg  px-3 py-1
              text-lg text-white" @click="closeModal">Zatvori</button>
          </template>
        </modal>
      </Teleport>
<Teleport to="body">
        <modal :show="showmsg" @close="showmsg = false">
          <template #header>
            <h3>Uspešno ste prijavili ovog korisnika!</h3>
          </template>
          <template #footer >
            <button class = "bg-red-600 rounded-2xl drop-shadow-lg  px-3 py-1
              text-lg text-white" @click="closeModal">Zatvori</button>
          </template>
        </modal>
      </Teleport>

    </div>
</template>
<script>
import Modal from "@/components/SuccessComp.vue";
import axios from 'axios';
export default {
    data() {
        return {
            report: {},
            showmsg: false,
            showerror: false,
        }
    },
    computed: {
        userId() {
            return this.$route.params.id;
        }
    },
    components: {
        Modal,
    },
    methods: {
    closeModal() {
        if(this.showerror === true) {
            this.showerror = false;
                
        }
        else {
            this.showmsg = false;
            this.$router.push('/');
           }

        },
    async postreport() {
            try {
        const response = await axios.post(`http://localhost:8080/api/v1/reports/${this.userId}`, this.report);
                if(response.status == 200) {
                    this.showmsg = true;
                }
                console.log(response);
            }
            catch(error) {
               this.showerror = true; 
            }
    },
    },
};
</script>
