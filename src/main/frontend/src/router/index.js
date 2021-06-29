import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Registration from '../views/Registration.vue'
import NotFound from '../views/NotFound.vue'
import store from '../store/index.js'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/register',
    name: 'Registration',
    component: Registration
  },  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/:notFound(.*)',
    component: NotFound
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/' || to.path === '') {
    if (store.state.token !== '') {
      next();
    } else {
      next('/login');
    }
  } else if (to.path === '/register' || to.path === '/login') {
    store.commit('logout');
    next();
  } else {
    next();
  }
})

export default router
