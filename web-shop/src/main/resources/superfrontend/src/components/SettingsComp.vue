<template>
<div class = "container w-[1200px] my-8 mx-auto">
        <form @submit.prevent = "editProfile">
        <div class = "flex flex-col justify-center items-center">
            <label for = "dateofbirth">Datum rodjenja</label>
        <input type = "text" v-model = "user.dateOfBirth" id = "dateofbirth"></input>
            <label for = "description">Opis profila</label>
        <input type = "text" v-model = "user.description" id = "description"></input>
            <label for = "imgpth">Profilna slika</label>
        <input type = "text" v-model = "user.imagePath" id = "imgpth"></input>
            <input type = "submit" value = "Izmeni podatke!"></input> 

        </div>
        </form>
    </div>
</template>
<script>
import { fetchSelf } from "./utils.js";
import axios from 'axios';
export default {
  data() {
    return {
      user: {},
    };
  },
  methods: {
    fetchSelf,
        async editProfile() {
            try {
                const response = await axios.patch(`http://localhost8080/api/v1/users/me`,this.user); 
                console.log(response);
            }
            catch(error) {
                
            }
        },
  },
  async mounted() {
    this.user = await this.fetchSelf();
  },
};
</script>
