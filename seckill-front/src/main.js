import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import Axios from "axios";
import qs from 'qs'
import {formatDate} from "./util/date-format";

Vue.prototype.$qs = qs
Vue.config.productionTip = false

Axios.defaults.baseURL = 'http://192.168.1.5:8083'
// Axios.defaults.baseURL = 'http://192.168.2.134:8080'
Axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
Axios.defaults.withCredentials = true

Vue.filter('date-format', function (value) {
  var date = new Date(value)
  return formatDate(date, 'yyyy-MM-dd hh:mm')
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
