import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import AllProducts from "@/views/Products.vue";
import store from "@/store";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("../views/Home.vue"),
    },
    {
      path: "/about",
      name: "about",

      component: () => import("../views/About.vue"),
    },
    {
      path: "/logout",
      name: "logout",
      component: () => import("../components/LogoutPage.vue"),
    },
    {
      path: "/register",
      name: "register",
      component: () => import("../components/RegisterPage.vue"),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../components/LoginPage.vue"),
    },
    {
      path: "/products",
      name: "products",
      component: () => import("../views/Products.vue"),
    },
    {
      path: "/products/:id",
      name: "product",
      component: () => import("../views/Product.vue"),
      props: true,
    },
    {
      path: "/me",
      name: "me",
      component: () => import("../views/MyProfile.vue"),
    },
    {
      path: "/postavi",
      name: "postavi",
      component: () => import("../views/PostProduct.vue"),
    },
    {
      path: "/settings",
      name: "settings",
      component: () => import("../views/SettingsView.vue"),
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: () => import("../views/DashboardView.vue"),
    },
    {
      path: "/:catchAll(.*)",
      redirect: "/register",
    },
  ],
});
router.beforeEach((to, from, next) => {
  const publicPages = ["/", "/products", "/login", "/register"];
  const authRequired = !publicPages.includes(to.path);

  const user = store.state.user;

  if (authRequired && !user) {
    return next("/login");
  }

  if (to.path === "/dashboard" && (!user || user.role !== "admin")) {
    return next("login");
  }

  next();
});

export default router;
