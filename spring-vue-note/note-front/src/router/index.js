import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "../components/home/Home";
import Register from "../components/Register";
import Login from "../components/Login";
import Index from "../components/Index";
import Bookshelf from "../components/bookshelf/Bookshelf";

Vue.use(VueRouter)

const routes = [
    {
        path: '/', name: 'Index', component: Index, redirect: '/home',
        children: [
            {path: '/home', name: "home", component: Home},
            {path: '/bookshelf', name: "bookshelf", component: Bookshelf},
        ]
    },
    {path: '/register', name: "Register", component: Register},
    {path: '/login', name: "login", component: Login},

]

const router = new VueRouter({
    routes
})

export default router
