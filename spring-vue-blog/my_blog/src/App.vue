<template>
  <div id="app" class="main">
    <nav-bar />
    <div class="main-container">
      <div class="left-container">
        <a-locale-provider :locale="locale">
          <router-view />
        </a-locale-provider>
      </div>
      <right />
    </div>
    <bottom />
    <a-back-top />
  </div>
</template>

<script>
import zhCN from 'ant-design-vue/lib/locale-provider/zh_CN'
import NavBar from './views/navbar/navbar'
import Right from './views/right/right'
import Bottom from './views/bottom/index'

export default {
  components: {
    NavBar, Right, Bottom
  },
  data() {
    return {
      current: ['1'],
      locale: zhCN
    }
  }

}
</script>

<style>
  .main {
    background-color: #f5f5f5;
  }
  .main-container {
    width: 1200px;
    margin: auto;
    display: flex;
    flex-direction: row;
  }

  .left-container {
    width: 850px;
    padding: 0 10px;
  }
</style>

