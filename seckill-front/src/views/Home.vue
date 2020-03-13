<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <br>
    --  {{username}}  --
    <br>
    <router-link to="/login" class="el-dialog--center">登录</router-link>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  components: {
  },
  computed: {
    username: function () {
      return this.$store.getters.getUserName
    }
  }
}
</script>
