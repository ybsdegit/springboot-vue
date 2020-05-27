const BASE_URL = process.env.NODE_ENV === 'production' ? 'iview-admin' : '/'

const path = require('path')
const resolve = dir => path.join(__dirname, dir)

module.exports = {
  lintOnSave: true,
  publicPath: BASE_URL,
  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src'))
      .set('_c', resolve('src/components'))
  },

  // 打包时不生成 .map 文件 加快打包速度
  productionSourceMap: false,

  // 跨域配置
  devServer: {
    port: 3336,
    proxy: 'http://localhost:9000'
    //   proxy: { // 配置代理，解决跨域请求后台数据的问题
    //     '/api': {
    //       target: 'http://localhost:9000/api', // 后台接口
    //       ws: true, // 是否跨域
    //       changeOrigin: true,
    //       pathRewrite: {
    //         '^/api': ''
    //       }
    //     }
    //   }
  }
}
