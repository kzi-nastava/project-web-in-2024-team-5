<template>
    <div class = "container w-[1200px] mx-auto">
    <div class = "flex flex-col font-[Open_Sans] my-4 text-2xl">
            <h2 class = "font-semibold text-3xl">Izmena podataka o proizvodu</h2>
            <div class = "bg-white rounded-2xl shadow-xl">
            <form @submit.prevent="postProduct" class = "flex flex-col p-2 space-y-4">


    <label class = "text-3xl font-bold" for = "name">Ime proizvoda</label>
    <input required v-model = "product.name" id = "name" form = "text" maxlength ="50" placeholder="Unesi ime proizvoda"></input>
    <label  class = "text-3xl font-bold" for = "price">Cena proizvoda</label>
    <input  required v-model = "product.price" id = "price" form = "number" placeholder="Unesi cenu proizvoda"></input>
    <label  class = "text-3xl font-bold" for = "desc">Opis</label>
    <input   required v-model = "product.description" id ="desc" maxlength = "100" form = "text" placeholder="Unesi opis proizvoda"></input>
    <label  class = "text-3xl font-bold" for = "category">Kategorija</label>
    <input   required v-model = "product.category" id = "category" form = "text" placeholder="Unesi kategoriju proizvoda"></input>

    <label  class = "text-3xl font-bold" for = "picture">Slika</label>
    <input   required v-model = "product.imagePath" id ="picture" form = "text"></input>

                    <input class ="justify-self-end drop-shadow-lg bg-[#004E9D] text-white h-full rounded-2xl " type = "submit" value ="Izmeni proizvod!"></input> 

            </form>
            </div>
    </div>
<Teleport to="body">
        <modal :show="showerror" @close="showerror = false">
          <template #header>
            <h3>Niste uspešno izmenili proizvod!</h3>
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
            <h3>Uspešno ste izmenili proizvod!</h3>
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
            product: {
                },
                showmsg: false,
                showerror: false,
        }
    },
    async mounted(){
    await this.fetchProduct();
    },
        computed: {
            productId() {
            return this.$route.params.id;
            }
        },
        components: {
            Modal,
        },
    methods: {
    closeModal() {
                if(this.showerror) {
                    this.showerror = false;
                }
                else {
                    this.showmsg = false;
                    this.$router.push(`/products/${this.productId}`)
                }
            },
    async fetchProduct() {
          try {
            const response = await axios.get(
              `http://localhost:8080/api/v1/products/${this.productId}`,
            );
            this.product = response.data;
            console.log(this.product)
          } catch (error) {}
        },
        async postProduct() {

            try{
                    const response = await axios.patch(`http://localhost:8080/api/v1/products/${this.productId}`,this.product);
                    this.showmsg = true;
                    console.log(response);
            }
            catch(error) {
                this.showerror = true;
            }
        },
    },
};
</script>
