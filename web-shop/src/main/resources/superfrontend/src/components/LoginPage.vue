<template>
  <div
    class="w-[1200px] flex flex-col justify-items-center items-center m-auto"
  >
    <div class="text-center">
      <h2>Ulogujte se.</h2>
      <p class="flex flex-row"></p>
      <form @submit.prevent="loginUser">
        <input
          class="border-[2px] border-black rounded-xl p-1"
          v-model="user.username"
          type="text"
          name="user.username"
          required
          placeholder="Unesi korisnicko ime"
        />
        <br />
        <br />
        <input
          class="border-[2px] border-black rounded-xl p-1"
          v-model="user.password"
          type="password"
          name="user.password"
          required
          placeholder="Unesi lozinku"
        />
        <br />
        <br />
        <button
          class="mt-10 rounded-lg bg-[#004E9D] text-white w-[225px] h-[40px] drop-shadow-lg"
          type="submit"
        >
          Login
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions } from "vuex";

export default {
  name: "LoginPage",
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    ...mapActions(["login"]),
    async loginUser() {
      try {
        await this.login(this.user);

        // Assuming user data is now in the store
        const user = this.$store.state.user;

        if (user) {
          // Emit event or perform further actions
          this.$emit("render");

          // Redirect or perform other actions after login
          this.$router.push("/dashboard");
        } else {
          console.error("Login failed: No user data received");
        }
      } catch (error) {
        console.error("Login failed:", error);
      }
    },
  },
};
</script>
