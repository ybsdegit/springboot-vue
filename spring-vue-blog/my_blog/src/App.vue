<template>
  <div id="app" class="main">
    <nav-bar class="metu-container" />
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
      locale: zhCN,
      live2d: 'shizuku'
    }
  },
  mounted() {
    this.initLive2d()
  },
  methods: {
    initLive2d() {
      setTimeout(() => {
        window.L2Dwidget.init({
          pluginRootPath: 'live2dw/',
          pluginJsPath: 'lib/',
          pluginModelPath: 'live2d-widget-model-shizuku/assets/',
          tagMode: false,
          debug: false,
          model: { jsonPath: '../live2dw/live2d-widget-model-shizuku/assets/shizuku.model.json' },
          display: { position: 'left', width: 240, height: 480 },
          mobile: { show: true },
          log: false
        })
      }, 1000)
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

  .metu-container {
    position: sticky;
    top: 0;
    z-index: 999;
    width: 100%;
    height: 70px;
  }
</style>

