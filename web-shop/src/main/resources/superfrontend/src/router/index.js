import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import AllProducts from "@/views/Products.vue";
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
      component: () => import("../views/LogoutView.vue"),
    },
    {
      path: "/register",
      name: "register",
      component: () => import("../components/RegisterPage.vue"),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/LoginView.vue"),
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
      path: "/products/:id/edit",
      name: "editproduct",
      component: () => import("../views/EditProductView.vue"),
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
      path: "/profile/:id",
      name: "profile",
      component: () => import("../views/ProfileView.vue"),
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: () => import("../views/DashboardView.vue"),
    },
    {
      path: "/reviews/:id",
      name: "reviews",
      component: () => import("../views/ReviewView.vue"),
    },
    {
      path: "/reports/:id",
      name: "reports",
      component: () => import("../views/ReportView.vue"),
    },
    {
      path: "/:catchAll(.*)",
      redirect: "/register",
    },
  ],
});
router.beforeEach(async (to, from, next) => {
  const publicPages = [
    "/",
    "/products",
    "/products/catchAll(*)",
    "/profile/catchAll(*)",
    "/me",
    "/postavi",
    "/settings",
    "/login",
    "/register",
  ];
  const productDetailRegex = /^\/products\/\d+$/;
  const profileDetailRegex = /^\/profile\/\d+$/;
  const authRequired =
    !publicPages.includes(to.path) &&
    !profileDetailRegex.test(to.path) &&
    !productDetailRegex.test(to.path);

  next();
});

export default router;
