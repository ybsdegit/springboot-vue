module.exports = {
  lintOnSave: true,

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
