<template>
    <div class="nav-bar">
        <el-menu
                :default-active="'/home'"
                class="el-menu-demo"
                mode="horizontal"
                router
                active-text-color="#25567A">
            <el-menu-item v-for="(item, i) in navList" :index="item.url" :key="i">
                {{item.name}}
            </el-menu-item>

            <el-submenu index="2" >
                <template slot="title">我</template>
                <el-menu-item index="/login">登录</el-menu-item>
                <el-menu-item index="/register">注册</el-menu-item>
            </el-submenu>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "NavMenu",

        data() {
            return {
                navList: [
                    {name: '首页', url: '/'},
                    {name: '书架', url: '/bookshelf'}
                ],
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>
    .nav-bar{
        width: 100%;
        left: 0px;
        top: 0px;
    }
    .el-menu-item{
        font-weight: bolder;
    }
</style>