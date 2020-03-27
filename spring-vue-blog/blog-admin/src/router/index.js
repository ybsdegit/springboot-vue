import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/article',
    component: Layout,
    redirect: '/article/blog',
    name: 'Article',
    meta: { title: '文章管理', icon: 'table' },
    children: [
      {
        path: 'type',
        name: 'Type',
        component: () => import('@/views/blog/type/type-list'),
        meta: { title: '分类管理', icon: 'table' }
      },
      {
        path: 'blog',
        name: 'Blog',
        component: () => import('@/views/blog/blog/blog-list'),
        meta: { title: '博客管理', icon: 'table' }
      },
      {
        path: 'about',
        name: 'About',
        component: () => import('@/views/blog/about/about-list'),
        meta: { title: '关于我的', icon: 'table' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
