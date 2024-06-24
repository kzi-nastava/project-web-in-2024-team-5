<template>
    <div class = "container w-[1200px] mx-auto">
    <div class = "flex flex-col font-[Open_Sans] my-4 text-2xl">
            <h2 class = "font-semibold text-3xl">Izmena podataka o proizvodu</h2>
            <div class = "bg-white rounded-2xl shadow-xl">
            <form @submit.prevent="postProduct" class = "flex flex-col p-2 space-y-4">


    <label class = "text-3xl font-bold" for = "name">Ime proizvoda</label>
    <input v-model = "product.name" id = "name" form = "text" maxlength ="50" placeholder="Unesi ime proizvoda"></input>
    <label  class = "text-3xl font-bold" for = "price">Cena proizvoda</label>
    <input v-model = "product.price" id = "price" form = "number" placeholder="Unesi cenu proizvoda"></input>
    <label  class = "text-3xl font-bold" for = "desc">Opis</label>
    <input v-model = "product.description" id ="desc" maxlength = "100" form = "text" placeholder="Unesi opis proizvoda"></input>
    <label  class = "text-3xl font-bold" for = "category">Kategorija</label>
    <input v-model = "product.category" id = "category" form = "text" placeholder="Unesi kategoriju proizvoda"></input>

    <label  class = "text-3xl font-bold" for = "picture">Slika</label>
    <input v-model = "product.imagePath" id ="picture" form = "text"></input>

                    <input class ="justify-self-end drop-shadow-lg bg-[#004E9D] text-white h-full rounded-2xl " type = "submit" value = "Postavi proizvod!"></input> 

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
            product: {
                },
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
    methods: {

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
                    console.log(response);
            }
            catch(error) {

            }
        },
    },
};
</script>
