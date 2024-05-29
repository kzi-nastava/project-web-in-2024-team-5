import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/About.vue')
    },
    {
      path: '/logout',
      name: 'logout',
      component: () => import('../components/LogoutPage.vue'), 
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../components/RegisterPage.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../components/LoginPage.vue')
    },
    {
      path: '/products',
      name: 'products',
      component: () => import('../components/ProductsPage.vue')
    },
    {
      path: '/:catchAll(.*)', redirect: '/register'
    }
  ]
});
router.beforeEach((to, from, next) => {
  const publicPages = ['/','/products','/login', '/register'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  if (authRequired && !loggedIn) {
    return alert ("You must first register or login!");  // Redirect to login if not authenticated
  }

  next();
});

export default router;
