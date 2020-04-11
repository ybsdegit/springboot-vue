<template>
  <div>
    <a-card title="友情链接" :headStyle="headStyle" :bodyStyle="bodyStyle">
      <div class="link-container">
        <a href="www.baidu.com">百度</a>
        <a href="www.baidu.com">百度</a>
        <a href="www.baidu.com">百度</a>
      </div>
    </a-card>
  </div>
</template>

<script>
  export default {
    name: "my-link",
    data() {
      return {
        headStyle: {
          fontSize: '18px',
          fontWeight: 'bold',
          lineHeight: '15px',
          borderLeft: '5px solid #409eff'
        },
        bodyStyle: {
          maxHeight: '530px',
          overflow: 'hidden'
        }
      }
    },
    methods: {
      test() {

      },
    }
  }

</script>

<style scoped>
  .link-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
  }

  .link-container a {
    font-size: 24px;
    width: 60px;
  }


</style>
