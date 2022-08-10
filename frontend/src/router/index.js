import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AuthRedirectHandler from '../views/AuthRedirectHandler.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/test',
    name: 'TestView',
    component: () =>
      import(
        /* webpackChunkName: "test", webpackPrefecth: true */ '../views/TestView.vue'
      )
  },
  {
    path: '/auth/redirect',
    name: 'AuthRedirectHandler',
    component: AuthRedirectHandler
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
