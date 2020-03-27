<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
  import typeApi from '@/api/type.js'
  export default {
    name: 'App',

    created() {
      this.getTypeList()
    },
    methods: {
      getTypeList() {
        typeApi.getList().then(res => {
          this.$store.commit('global/SET_TYPE', res.data)
        })
      },
    }
  }
</script>

<style>
  .add-button {
    margin-bottom: 15px;
  }
  .pagination {
    margin-bottom: 15px;
  }
</style>

