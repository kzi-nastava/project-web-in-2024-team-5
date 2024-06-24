<template>
  <div class="container w-[1200px] mx-auto">
    <h1>Here you can log out!</h1>
    <button @click="logoutUser">Logout</button>
  </div>
</template>

<script>
import axios from "axios";
import { fetchSelf } from "./utils";
export default {
  async mounted() {
    const res = await fetchSelf()
    if (!res) {
      this.$router.push("/")
    }
  },
  methods: {
    async logoutUser() {
      try {
        const res = axios.post("http://localhost:8080/api/v1/logout")
        this.$router.push("/");
        // this.$emit("userLoggedOut");
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style></style>

