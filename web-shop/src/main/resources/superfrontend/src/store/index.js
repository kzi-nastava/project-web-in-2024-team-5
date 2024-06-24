import { createStore } from "vuex";

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
    login({ commit }, user) {
      commit("setUser", user);
    },
    logout({ commit }) {
      commit("clearUser");
    },
  },
  getters: {
    isAuthenticated: (state) => !!state.user, // Returns true if user is not null
    userRole: (state) => (state.user ? state.user.role : null), // Returns the role of the user if user is not null
  },
  modules: {},
});
