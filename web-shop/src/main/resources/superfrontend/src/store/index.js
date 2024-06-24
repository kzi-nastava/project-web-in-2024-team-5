// store/index.js
import { createStore } from "vuex";
import axios from "axios";

export default createStore({
  state: {
    user: null,
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    clearUser(state) {
      state.user = null;
    },
  },
  actions: {
    async login({ commit }, user) {
      try {
        const response = await axios.post("http://localhost:8080/api/v1/login", user, { withCredentials: true });
        // Assuming the response contains the necessary user information
        commit("setUser", response.data);
      } catch (error) {
        console.error("Login failed:", error);
      }
    },
    async logout({ commit }) {
      try {
        await axios.post("http://localhost:8080/api/v1/logout", {}, { withCredentials: true });
        commit("clearUser");
      } catch (error) {
        console.error("Logout failed:", error);
      }
    },
  },
  getters: {
    isAuthenticated: (state) => !!state.user,
    userRole: (state) => (state.user ? state.user.role : null),
  },
});
