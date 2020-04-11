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
            <a-icon type="mail"/>
            首页
          </a-menu-item>
          <a-menu-item key="2">
            <a-icon type="mail"/>
            分类
          </a-menu-item>
          <a-menu-item key="3">
            <a-icon type="mail"/>
            时间轴
          </a-menu-item>
          <a-menu-item key="4">
            <a-icon type="mail"/>
            关于我
          </a-menu-item>
        </a-menu>
        <a-input-search placeholder="搜索你想要的" style="width: 200px" @search="onSearch"/>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "navbar",
    data() {
      return {
        current: ['1'],
      };
    },
    methods: {
      onSearch(value) {
        console.log(value);
      },
    },
  };
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

</style>
