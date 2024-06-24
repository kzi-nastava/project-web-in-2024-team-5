<template>
  <section class="py-14">
    <div class="w-[1200px] flex flex-col justify-items-center items-center m-auto">
      <div class="text-center">
        <h1 class="text-2xl font-bold mb-6 text-center">
          Izlogujte se
        </h1>
        <p class="flex flex-row"></p>
        <button class="mt-2 rounded-lg bg-[#B84E15] text-white w-[225px] h-[40px] drop-shadow-lg"
          @click.on="logoutUser">
          Logout
        </button>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
import { fetchSelf } from "./utils";
export default {
  async mounted() {
    const res = await fetchSelf();
    if (!res) {
      this.$router.push("/");
    }
  },
  methods: {
    async logoutUser() {
      try {
        const res = axios.post("http://localhost:8080/api/v1/logout");
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
