<template>
  <header>
    <main>
      <section class="bg-[#FBFBF9]">
        <div class="row1 bg-[#004E9D] flex flex-row justify-center">
          <div
            class="container text-white font-['Open_Sans'] w-[1200px] flex flex-row justify-between gap-5 p-[40px]"
          >
            <RouterLink to="/">
              <div class="text-2xl">SuperWebShop</div>
            </RouterLink>
            <div>
              <input
                class="pl-5 rounded-full w-[600px] h-[35px] text-black text-base"
                type="text"
                placeholder="Search"
              />
            </div>
            <ProfileIcon :user="user" v-if="isLogged" />
            <div
              class="flex flex-row justify-center align-middle items-center gap-10"
            >
              <RouterLink v-if="!isLogged" to="/login">
                <button
                  class="drop-shadow-xl rounded-xl w-[76px] h-[27px] bg-white text-s text-black"
                >
                  Login
                </button>
              </RouterLink>
              <RouterLink v-if="!isLogged" to="/register">
                <button
                  class="drop-shadow-xl rounded-xl w-[105px] h-[41px] bg-[#B84E15]"
                >
                  Register
                </button>
              </RouterLink>
            </div>
          </div>
        </div>
        <div class="row2 flex flex-row pt-2 justify-center m-auto">
          <div
            class="container text-black font-['Open_Sans'] w-[1200px] flex flex-row justify-between"
          >
            <RouterLink to="/products">
              <button
                class="drop-shadow-lg rounded-xl w-[152px] h-[35px] bg-[#D9D9D9] text-lg text-bla k"
              >
                Proizvodi
              </button>
            </RouterLink>
            <RouterLink to="/about"
              ><button
                class="drop-shadow-lg rounded-xl w-[152px] h-[35px] bg-[#B84E15] text-lg text-white"
              >
                Postavi oglas
              </button></RouterLink
            >
          </div>
        </div>
      </section>
    </main>
  </header>
</template>

<script>
import axios from "axios";
import ProfileIcon from "@/components/ProfileIcon.vue";
export default {
  components: {
    ProfileIcon,
  },
  data() {
    return {
      user: {},
      isLogged: false,
    };
  },
  mounted() {
    this.loggedUser();
  },
  methods: {
    handleLogout() {
      this.isLogged = false;
    },
    async loggedUser() {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/users/me",
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          },
        );
        if (response.status === 200) {
          console.log(response);
          this.user = response.data;
          this.isLogged = true;
        }
      } catch (error) {
        console.log(error);
        if (error.response && error.response.status === 401) {
          alert("You're not logged in!");
          this.isLogged = false;
        }
      }
    },
  },
};
</script>
