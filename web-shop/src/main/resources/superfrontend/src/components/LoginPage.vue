<template>
  <div class="w-[1200px] flex flex-col justify-items-center items-center m-auto">
    <div v-if="!isLoggedIn" class="text-center">
      <h2>Ulogujte se.</h2>
      <p class="flex flex-row"></p>
      <form @submit.prevent="loginUser">
        <input class="border-[2px] border-black rounded-xl p-1" v-model="user.username" type="text" name="user.username"
          required placeholder="Unesi korisnicko ime" />
        <br />
        <br />
        <input class="border-[2px] border-black rounded-xl p-1" v-model="user.password" type="password"
          name="user.password" required placeholder="Unesi lozinku" />
        <br />
        <br />
        <button class="mt-10 rounded-lg bg-[#004E9D] text-white w-[225px] h-[40px] drop-shadow-lg" type="submit">
          Login
        </button>
      </form>
    </div>
  </div>
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
      this.$router.push("/")
    }
  },
  methods: {
    async loginUser() {
      try {
        const response = await axios.post("http://localhost:8080/api/v1/login", this.user, { withCredentials: true });
        if(response.data.role === "admin") {
          this.$router.push("/dashboard")
        }
        else if(response.data) {
          this.$router.push("/")
        }
      } catch (error) {
        console.error("Login failed:", error);
      }
    },
  },
};
</script>
