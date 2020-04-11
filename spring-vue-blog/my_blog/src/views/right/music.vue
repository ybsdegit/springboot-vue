<template>
  <div>
    <a-card title="音乐欣赏" :headStyle="headStyle" :bodyStyle="bodyStyle">
      <div class="">
        音乐欣赏
      </div>
    </a-card>
  </div>
</template>

<script>
  export default {
    name: "music",
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

<style>


</style>
