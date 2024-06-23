<template>
    <div class = "container w-[1200px] mx-auto">
    <div class = "flex flex-col font-[Open_Sans] my-4 text-2xl">
            <h2 class = "font-semibold text-3xl">Postavljanje proizvoda na prodaju!</h2>
            <div class = "bg-white rounded-2xl shadow-xl">
            <form @submit.prevent="postProduct" class = "flex flex-col p-2 space-y-4">
    <label for = "name">Ime proizvoda</label>
    <input v-model = "product.name" form = "text" maxlength ="50" placeholder="Unesi ime proizvoda"></input>
    <label for = "name">Cena proizvoda</label>
    <input v-model = "product.price" form = "number" placeholder="Unesi cenu proizvoda"></input>
    <label for = "name">Opis</label>
    <input v-model = "product.description" maxlength = "100" form = "text" placeholder="Unesi opis proizvoda"></input>
    <label for = "name">Tip prodaje</label>
    <select v-model = "product.typeOfSale">
                            <option>Fiksna cena</option>
                            <option>Aukcija</option>
                        </select>
    <label for = "name">Kategorija</label>
    <input v-model = "product.category" form = "text" placeholder="Unesi kategoriju proizvoda"></input>

    <label for = "name">Slika</label>
    <input v-model = "product.imagePath" form = "text" placeholder="Unesi URL slike proizvoda"></input>

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
    methods: {
        async postProduct() {

            try{
                    const response = await axios.post("http://localhost:8080/api/v1/products",this.product);
                    console.log(response);
            }
            catch(error) {

            }
        },
    },
};
</script>
