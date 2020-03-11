import Vue from 'vue'
import VueRouter from 'vue-router'
import User from "../views/User";
import ManageUser from "../views/ManageUser";
import AddUser from "../views/AddUser";
import App from "../App";
import Index from "../views/Index";
import UpdateUser from "../views/UpdateUser";

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "用户管理",
    component: Index,
    redirect: "/manageUser",
    children: [
      {
        path: "/manageUser",
        name: "查询用户",
        component: ManageUser,
      },
      {
        path: "/addUser",
        name: "添加用户",
        component: AddUser,
      }
    ]
  },
  {
    path: "/updateUser",
    component: UpdateUser,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
