import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login";
import Register from "../views/Register";
import HomeTemplate from "../views/HomeTemplate"
import CourseList from "../views/CourseList";
import OrderList from "../views/OrderList";
import Course from "../views/Course";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/home',
    name: 'HomeTemplate',
    component: HomeTemplate,
    children: [
      {path: 'course/list', component: CourseList},
      {path: 'order/list', component: OrderList},
      {path: 'course/:courseNo', component: Course},
    ]
  }


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
