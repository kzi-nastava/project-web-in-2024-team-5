import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import AllProducts from '@/views/Products.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home'
    },
    {
      path: '/about',
      name: 'about',
      
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
      component: () => import('../views/Products.vue')
    },
    {
      path: '/products/:id',
      name: 'product',
      component: () => import('../components/ProductPage.vue'),
      props: true
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
    return alert ("You must first register or login!"); 
  }

  next();
});

export default router;
