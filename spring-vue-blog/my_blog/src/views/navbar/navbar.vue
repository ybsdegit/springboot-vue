<template>
  <div>
    <div class="menu-container">
      <div class="nav-logo">
        <img src="@/assets/logo.png" class="logo-img">
        <span class="logo-text">Paulson Blog</span>
      </div>
      <div class="nav-menu-search">
        <a-menu v-model="current" mode="horizontal">
          <a-menu-item key="1">
            <router-link to="/">
              <a-icon type="mail" />首页
            </router-link>
          </a-menu-item>
          <a-menu-item key="2">
            <router-link to="/type">
              <a-icon type="mail" />分类
            </router-link>
          </a-menu-item>
          <a-menu-item key="3">
            <router-link to="/timeLine">
              <a-icon type="mail" />时间轴
            </router-link>
          </a-menu-item>
          <a-menu-item key="4">
            <router-link to="/about">
              <a-icon type="mail" />关于我
            </router-link>
          </a-menu-item>
        </a-menu>
        <a-input-search placeholder="搜索你想要的" style="width: 200px" @search="onSearch" />
      </div>
      <router-link v-if="!user.username" to="/login" class="login">登录/注册</router-link>
      <router-link v-else to="/manage" class="login-header">
        <img :src="user.header" class="login-header-img">
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
  data() {
    return {
      current: ['1'],
      user: this.$store.getters.getUser
    }
  },
  methods: {
    onSearch(value) {
      console.log(value)
    }
  }
}
</script>

<style>
  .menu-container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    padding: 0px 60px 0px 60px;
    line-height: 70px;
    background-color: #fff;
    margin-bottom: 10px;
  }

  .nav-logo {
    vertical-align: middle;
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .nav-menu-search {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    margin-right: 40px;
  }

  .logo-img {
    height: 40px;
    width: 40px;
    margin-right: 10px;
  }

  .logo-text {
    font-size: 30px;
  }

  .ant-menu {
    font-size: 18px !important;
  }

  .ant-menu-horizontal {
    border-bottom: none !important;
  }

  .login-header{
   color: black;
   margin-left: 20px;
  }
  .login-header-img {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    border: 3px solid #e5e5e5;
  }

</style>
