import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    name: 'Index',
    component: () => import('../views/index/index')
  },
  {
    path: '/type',
    name: 'Type',
    component: () => import('../views/type/type')
  },
  {
    path: '/timeLine',
    name: 'TimeLime',
    component: () => import('../views/time-line/time-line')
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/about/about')
  },
  {
    path: '/info/:id',
    name: 'Info',
    component: () => import('../views/blog/index')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
