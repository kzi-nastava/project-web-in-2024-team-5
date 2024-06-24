<template>
<div class = "font-[Open_Sans] container w-[1200px] my-8 mx-auto"> 
        <h2 class = "text-4xl text-center m-4 ">Izmena osnovih informacija </h2>
        <form @submit.prevent = "editProfile">

                    <div class = "flex flex-col text-2xl w-1/2 m-auto bg-white rounded-2xl drop-shadow-xl justify-center items-center">
<label for = "name">Ime</label>
        <input class = "rounded-2xl border-2 p-2 border-gray-500" type = "text" v-model = "user.name" id = "name"></input>

            <label for = "lastname">Prezime</label>
        <input  class = "rounded-2xl border-2 p-2 border-gray-500" type = "text" v-model = "user.lastname" id = "lastname"></input>

            <label for = "number">Broj telefona</label>
        <input  class = "rounded-2xl border-2 p-2 border-gray-500" type = "text" v-model = "user.phoneNumber" id = "number"></input>

            <label for = "dateofbirth">Datum rodjenja</label>
        <input  class = "rounded-2xl border-2 p-2 border-gray-500" type = "datetime-local" v-model = "user.dateOfBirth" id = "dateofbirth"></input>
            <label for = "description">Opis profila</label>
        <input  class = "rounded-2xl border-2 p-2 border-gray-500" type = "text" v-model = "user.description" id = "description"></input>
            <label for = "imgpth">Profilna slika</label>
        <input  class = "rounded-2xl border-2 p-2 border-gray-500" type = "text" v-model = "user.profilePicture" id = "imgpth"></input>
                <div class = "p-2 m-4 w-fit rounded-2xl text-white drop-shadow-lg bg-blue-500 hover:bg-blue-700">
            <input type = "submit" value = "Izmeni podatke!"></input> 
                </div>

        </div>
        </form>
        <div class = "m-auto flex w-1/2 flex-col " v-if="!checkPassword">
            <form @submit.prevent = "authPass">
        <h2 class = "text-4xl text-center m-4 ">Izmena sigurnosnih podataka</h2>
        <p class = "text-xl text-center mx-auto">Kako biste izmenili svoje sigurnosne podatke morate potvrditi svoju trenutnu lozinku.</p>

        <input  class = "w-full rounded-2xl border-2 p-2 border-gray-500" type = "text" v-model = "pass" placeholder="Unesite trenutnu lozinku"></input>
<div class = "p-2 my-4  text-center text-2xl font-semibold w-full rounded-2xl text-white drop-shadow-lg bg-blue-500 hover:bg-blue-700">
            <input type = "submit" value = "Potvrdi lozinku"></input> 
                </div>

        </form> 
        </div>
        <div v-if ="checkPassword">

        <h2 class = "text-4xl text-center m-4 ">Izmena sigurnosnih podataka</h2>
        <h2 class = "text-xl text-center m-4 ">Uspesno ste potvrdili svoju lozinku!</h2>
<form @submit.prevent = "editProfile">

                    <div class = "flex flex-col text-2xl w-1/2 m-auto bg-white rounded-2xl drop-shadow-xl justify-center items-center">
<label for = "email">Email adresa</label>
        <input class = "rounded-2xl border-2 p-2 border-gray-500" type = "text" v-model = "user.email" id = "email"></input>

            <label for = "password">Lozinka</label>
        <input  class = "rounded-2xl border-2 p-2 border-gray-500" type = "password" v-model = "user.password" id = "password"></input>

            <label for = "username">Korisničko ime</label>
        <input  class = "rounded-2xl border-2 p-2 border-gray-500" type = "text" v-model = "user.username" id = "username"></input>
               <div class = "p-2 m-4 w-fit rounded-2xl text-white drop-shadow-lg bg-blue-500 hover:bg-blue-700">
            <input type = "submit" value = "Izmeni podatke!"></input> 
                </div>

        </div>
        </form>
                    </div>
    </div>
</template>
<script>
import { fetchSelf } from "./utils.js";
import axios from 'axios';
export default {
  data() {
    return {
      user: {},
    checkPassword: false,
    pass: "",
    };
  },
  methods: {
    fetchSelf,
        async editProfile() {
            try {
                const response = await axios.patch(`http://localhost:8080/api/v1/users/me`,this.user); 
                console.log(response);
            }
            catch(error) {
                
            }
        },
        async authPass() {
            const response = await axios.post("http://localhost:8080/api/v1/users/me/password", { 
                    username: this.user.username,
                password: this.pass
            });
            if(response.status === 200) {
                this.checkPassword = true;
                console.log(this.checkPassword);
                this.$forceUpdate();
            }
        },
  },
  async mounted() {
    this.user = await this.fetchSelf();
  },
};
</script>
