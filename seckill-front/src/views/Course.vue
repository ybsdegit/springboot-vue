<template>
  <div class="course">
    <img alt="Vue logo" src="../assets/logo.png">
    <br>
    --  {{username}}  --
    <br>
    <router-link to="/login" class="el-dialog--center">登录</router-link>
  </div>
</template>

<script>

export default {
  name: 'course',
  components: {
  },
  computed: {
    username: function () {
      return this.$store.getters.getUserName
    }
  }
}
</script>
