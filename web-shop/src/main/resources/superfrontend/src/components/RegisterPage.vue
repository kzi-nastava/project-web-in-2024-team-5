<template>
  <section class="py-16">
    <div class="container w-[1200px] mx-auto flex flex-col items-center">
      <div class="max-w-md p-8 bg-white rounded-lg shadow-lg">
        <h1 class="text-2xl font-bold mb-6 text-center">
          Please enter credentials needed for registration.
        </h1>
        <form @submit.prevent="register">
          <input
            v-model="user.name"
            type="text"
            name="name"
            required
            placeholder="Enter Name"
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
          />
          <br />
          <input
            v-model="user.lastname"
            type="text"
            name="lastname"
            required
            placeholder="Enter Last Name"
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
          />
          <br />
          <input
            v-model="user.username"
            type="text"
            name="username"
            required
            placeholder="Enter Username"
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
          />
          <br />
          <input
            v-model="user.password"
            type="password"
            name="password"
            required
            placeholder="Enter Password"
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
          />
          <br />
          <input
            v-model="user.email"
            type="email"
            name="email"
            required
            placeholder="Enter Email"
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
          />
          <br />
          <input
            v-model="user.phoneNumber"
            type="text"
            name="phoneNumber"
            required
            placeholder="Enter Phone Number"
            class="w-full px-4 py-2 mb-2 border border-gray-300 rounded-lg"
          />
          <br />
          <div class="flex items-center space-x-2">
            <input
              v-model="user.role"
              type="radio"
              checked
              name="role"
              value="buyer"
            />
            <label for="buyer">Buyer</label>
          </div>

          <div class="flex items-center space-x-2">
            <input
              v-model="user.role"
              type="radio"
              name="role"
              value="seller"
            />
            <label for="seller">Seller</label>
          </div>
          <br />
          <button
            type="submit"
            value="Register"
            class="w-full px-4 py-2 bg-[#B84E15] text-white font-bold rounded-lg"
          >
            Register
          </button>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      user: {
        name: "",
        lastname: "",
        username: "",
        password: "",
        email: "",
        phoneNumber: "",
        role: "buyer",
      },
    };
  },
  methods: {
    async register() {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/v1/register",
          this.user,
          {
            headers: {
              "Content-Type": "application/json", // Set content type to JSON
            },
          }
        );
        if (response.status === 200) {
          this.$router.push("/settings");
        } else {
          alert("Registration failed");
        }
      } catch (error) {
        if (error.response && error.response.status === 403) {
          alert("You're already logged in");
        } else {
          alert("Registration failed");
        }
      }
    },
  },
};
</script>

<style scoped></style>
