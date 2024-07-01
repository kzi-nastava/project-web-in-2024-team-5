<template>
  <section class="py-16">
    <div
      class="font-[Open_Sans] container w-[1200px] mx-auto flex flex-col items-center"
    >
      <div class="max-w-md p-8 bg-white rounded-lg shadow-lg">
        <h2 class="text-2xl font-bold mb-6 text-center">
          Izmena osnovih informacija
        </h2>
        <form @submit.prevent="editProfile">
          <input
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
            placeholder="Ime"
            type="text"
            v-model="user.name"
            id="name"
          />

          <input
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
            placeholder="Prezime"
            type="text"
            v-model="user.lastname"
            id="lastname"
          />

          <input
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
            placeholder="Broj telefona"
            type="text"
            v-model="user.phoneNumber"
            id="number"
          />

          <input
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
            placeholder="Datum rodjenja"
            type="date"
            v-model="user.dateOfBirth"
            id="dateofbirth"
          />

          <textarea
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
            placeholder="Opis profila"
            type="text"
            rows="2"
            v-model="user.description"
            id="description"
          >
          </textarea>

          <input
            class="w-full px-4 py-2 mb-6 border border-gray-300 rounded-lg"
            placeholder="Profilna slika"
            type="text"
            v-model="user.profilePicture"
            id="imgpth"
          />
          <div
            class="w-full px-4 py-2 bg-[#B84E15] text-white font-bold rounded-lg"
          >
            <input type="submit" value="Izmeni podatke!" />
          </div>
        </form>
      </div>
      <div class="m-auto flex w-1/2 flex-col" v-if="!checkPassword">
        <form @submit.prevent="authPass">
          <h2 class="text-4xl text-center m-4">Izmena sigurnosnih podataka</h2>
          <p class="text-xl text-center mx-auto">
            Kako biste izmenili svoje sigurnosne podatke morate potvrditi svoju
            trenutnu lozinku.
          </p>

          <input
            class="w-full rounded-2xl border-2 p-2 border-gray-500"
            type="password"
            v-model="pass"
            placeholder="Unesite trenutnu lozinku"
          />
          <div
            class="p-2 my-4 text-center text-2xl font-semibold w-full rounded-2xl text-white drop-shadow-lg bg-blue-500 hover:bg-blue-700"
          >
            <input type="submit" value="Potvrdi lozinku" />
          </div>
        </form>
      </div>
      <div v-if="checkPassword">
        <h2 class="text-4xl text-center m-4">Izmena sigurnosnih podataka</h2>
        <h2 class="text-xl text-center m-4">
          Uspesno ste potvrdili svoju lozinku!
        </h2>
        <form @submit.prevent="editProfile">
          <div
            class="flex flex-col text-2xl w-1/2 m-auto bg-white rounded-2xl drop-shadow-xl justify-center items-center"
          >
            <label for="email">Email adresa</label>
            <input
              class="rounded-2xl border-2 p-2 border-gray-500"
              type="text"
              v-model="user.email"
              id="email"
            />

            <label for="password">Lozinka</label>
            <input
              class="rounded-2xl border-2 p-2 border-gray-500"
              type="password"
              v-model="newpass"
              id="password"
            />

            <label for="username">Korisničko ime</label>
            <input
              class="rounded-2xl border-2 p-2 border-gray-500"
              type="text"
              v-model="user.username"
              id="username"
            />
            <div
              class="p-2 m-4 w-fit rounded-2xl text-white drop-shadow-lg bg-blue-500 hover:bg-blue-700"
            >
              <input type="submit" value="Izmeni podatke!" />
            </div>
          </div>
        </form>
      </div>
      <Teleport to="body">
        <modal :show="showpwerror" @close="showpwerror = false">
          <template #header>
            <h3>Niste uspešno potvrdili svoju lozinku!</h3>
          </template>
          <template #footer>
            <button
              class="bg-red-600 rounded-2xl drop-shadow-lg px-3 py-1 text-lg text-white"
              @click="closeModal"
            >
              Zatvori
            </button>
          </template>
        </modal>
      </Teleport>
      <Teleport to="body">
        <modal :show="showerror" @close="showerror = false">
          <template #header>
            <h3>Niste uspešno izmenili svoj profil!</h3>
          </template>
          <template #footer>
            <button
              class="bg-red-600 rounded-2xl drop-shadow-lg px-3 py-1 text-lg text-white"
              @click="closeModal"
            >
              Zatvori
            </button>
          </template>
        </modal>
      </Teleport>
      <Teleport to="body">
        <modal :show="showmsg" @close="showmsg = false">
          <template #header>
            <h3>Uspešno ste izmenili svoj profil!</h3>
          </template>
          <template #footer>
            <button
              class="bg-red-600 rounded-2xl drop-shadow-lg px-3 py-1 text-lg text-white"
              @click="closeModal"
            >
              Zatvori
            </button>
          </template>
        </modal>
      </Teleport>
    </div>
  </section>
</template>
<script>
import Modal from "@/components/SuccessComp.vue";
import { fetchSelf } from "./utils.js";
import axios from "axios";
export default {
  data() {
    return {
      showmsg: false,
      showerror: false,
      showpwerror: false,
      user: {},
      checkPassword: false,
      pass: "",
      newpass: "",
    };
  },
  methods: {
    fetchSelf,
    closeModal() {
      if (this.showerror) {
        this.showerror = false;
      } else if (this.showpwerror) {
        this.showpwerror = false;
      } else {
        this.showmsg = false;
        this.$router.push("/me");
      }
    },
    async editProfile() {
      try {
        if (this.user.email.length == 0 || this.user.username.length == 0) {
          throw new Error("greska");
        }
        if (this.newpass.length == 0) {
          const response = await axios.patch(
            `http://localhost:8080/api/v1/users/me`,
            { email: this.user.email, username: this.user.username }
          );
          this.showmsg = true;
          return;
        }
        const response = await axios.patch(
          `http://localhost:8080/api/v1/users/me`,
          {
            email: this.user.email,
            username: this.user.username,
            password: this.newpass,
          }
        );
        this.showmsg = true;
        console.log(response);
      } catch (error) {
        this.showerror = true;
      }
    },
    async authPass() {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/v1/users/me/password",
          {
            username: this.user.username,
            password: this.pass,
          }
        );
        if (response.status === 200) {
          this.checkPassword = true;
          console.log(this.checkPassword);
          this.$forceUpdate();
        }
      } catch (error) {
        this.showpwerror = true;
      }
    },
  },

  components: {
    Modal,
  },
  async mounted() {
    this.user = await this.fetchSelf();
  },
};
</script>
