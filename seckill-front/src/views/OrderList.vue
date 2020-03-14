<template>
  <div class="course-list">
    <img alt="Vue logo" src="../assets/logo.png">
    <br>
    --  {{username}}  --
    <br>
    <router-link to="/login" class="el-dialog--center">登录</router-link>
  </div>
</template>

<script>

export default {
  name: 'course-list',
  components: {
  },
  computed: {
    username: function () {
      return this.$store.getters.getUserName
    }
  }
}
</script>
