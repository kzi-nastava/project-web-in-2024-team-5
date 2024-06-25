<template>
  <section class="py-16">
    <div
      class="w-[1200px] flex flex-col justify-items-center items-center m-auto"
    >
      <div
        v-if="!isLoggedIn"
        class="max-w-md p-8 bg-white rounded-lg shadow-lg"
      >
        <h1 class="text-2xl font-bold mb-6 text-center">Ulogujte se</h1>
        <p class="flex flex-row"></p>
        <form @submit.prevent="loginUser">
          <input
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
            v-model="user.username"
            type="text"
            name="user.username"
            required
            placeholder="Unesi korisnicko ime"
          />
          <br />
          <input
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
            v-model="user.password"
            type="password"
            name="user.password"
            required
            placeholder="Unesi lozinku"
          />
          <br />
          <br />
          <button
            class="w-full px-4 py-2 bg-[#B84E15] text-white font-bold rounded-lg"
            type="submit"
          >
            Login
          </button>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
import { fetchSelf } from "./utils";

export default {
  name: "LoginPage",
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      isLoggedIn: false,
    };
  },
  async mounted() {
    const res = await fetchSelf();
    if (res) {
      this.$router.push("/");
    }
  },
  methods: {
    async loginUser() {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/v1/login",
          this.user,
          { withCredentials: true }
        );
        if (response.data.role === "admin") {
          this.$router.push("/dashboard");
        } else if (response.data) {
          this.$router.push("/");
        }
      } catch (error) {
        window.alert("Niste uneli dobru lozinku ili ovaj korisnik ne postoji");
        console.error("Login failed:", error);
      }
    },
  },
};
</script>
