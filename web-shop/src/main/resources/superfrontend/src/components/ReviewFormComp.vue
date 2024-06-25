<template>
    <div class = "container w-[1200px] mx-auto">
    <div class = "flex flex-col font-[Open_Sans] my-4 text-2xl">
            <h2 class = "font-semibold text-3xl">Popunite formu za ocenjivanje korisnika.</h2>
            <div class = "bg-white rounded-2xl shadow-xl">
            <form @submit.prevent="postReview" class = "flex flex-col p-2 space-y-4">
    <label for = "score">Ocena</label>
    <input v-model = "review.score" form = "text" maxlength ="50" placeholder="Unesi ocenu za korisnika" id = "score"></input>
    <label for = "comment">Komentar</label>
    <input v-model = "review.comment" form = "text" placeholder="Unesi komentar" id = "comment"></input>

                    <input class ="justify-self-end drop-shadow-lg bg-[#004E9D] text-white h-full rounded-2xl " type = "submit" value = "Postavi recenziju"></input> 

            </form>
            </div>
    </div>
<Teleport to="body">
        <modal :show="showerror" @close="showerror= false">
          <template #header>
            <h3>Niste uspešno ocenili ovog korisnika!</h3>
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
            <h3>Uspešno ste ocenili ovog korisnika!</h3>
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
import Modal from '@/components/SuccessComp.vue';
import axios from 'axios';
export default {
    data() {
        return {
            review: {},
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
    async postReview() {
            try {
        const response = await axios.post(`http://localhost:8080/api/v1/reviews/${this.userId}`, this.review);
                if(response.status == 201) {
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
