import axios from 'axios'
import store from '@/store'
import { getToken } from '@/utils/auth'
import { message } from 'ant-design-vue'

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  timeout: 5000 // request timeout
})

service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['Authorization'] = getToken()
    }
    return config
  },
  error => {
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

service.interceptors.response.use(

  response => {
    const res = response.data

    if (res.code && res.code !== 20000) {
      // if (res.code === 40003) {
      //   // 弹窗提示报错
      //
      //   // store.commit('SET_USER', {})
      // }
      message.error(res.msg)
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    message.error('Request failed with status code 404')
    return Promise.reject(error)
  }
)

export default service
