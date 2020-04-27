import router from './router'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whitelist = ['/login', '/']

/**
 * 在路由跳转之前进行一些操作
 * to：跳转的目标路由
 * from：表示从哪个路由进行跳转
 * next：这是一个函数，用于控制路由跳转（this.$router.push()）
 */
router.beforeEach(async(to, from, next) => {
  console.log(to, from, next)
  NProgress.start()
  next()
  // const path = to.path
  // if (whitelist.indexOf(path) === -1) {
  //   // 不在白名单，跳转到登录页
  //   next('/login')
  // } else {
  //   // 在白名单，继续下一页。如果是登录页，跳转到首页
  //   next()
  // }
  NProgress.done()
})

// 在路由跳转之后进行一系列的操作
router.afterEach(() => {
  NProgress.done()
})
