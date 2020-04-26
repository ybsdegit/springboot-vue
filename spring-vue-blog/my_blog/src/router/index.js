import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/index',
    name: 'Layout',
    component: () => import('../views/layout/index'),
    children: [
      {
        path: '/index',
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
        path: '/manage',
        name: 'Manage',
        redirect: 'userInfo',
        component: () => import('../views/manage/index'),
        children: [
          {
            path: '/userInfo',
            name: 'UserInfo',
            component: () => import('../views/manage/user-info')
          },
          {
            path: '/collection',
            name: 'Collection',
            component: () => import('../views/manage/user-collection')
          },
          {
            path: '/comment',
            name: 'Comment',
            component: () => import('../views/manage/user-comment')
          }
        ]
      },
      {
        path: '/info/:id',
        name: 'Info',
        component: () => import('../views/blog/index')
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/login/index')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
