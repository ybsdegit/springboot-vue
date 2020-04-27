import router from './router'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth'
NProgress.configure({ showSpinner: false }) // NProgress Configuration

const blacklist = ['/manage/userInfo', '/manage/collection', '/manage/comment']

/**
 * 在路由跳转之前进行一些操作
 * to：跳转的目标路由
 * from：表示从哪个路由进行跳转
 * next：这是一个函数，用于控制路由跳转（this.$router.push()）
 */
router.beforeEach(async(to, from, next) => {
  NProgress.start()

  const token = getToken()
  if (token) {
    next()
  } else {
    // token不存在，判断当前路由是否是黑名单
    if (blacklist.indexOf(to.path) >= 0) {
      next('/login')
    } else {
      next()
    }
  }
  NProgress.done()
})

// 在路由跳转之后进行一系列的操作
router.afterEach(() => {
  NProgress.done()
})
