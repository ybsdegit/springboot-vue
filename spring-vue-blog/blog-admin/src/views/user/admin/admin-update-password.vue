<template>
  <div>
    <el-form ref="form" :model="adminUser" label-width="80px" size="mini">
      <el-form-item label="账号">
        <el-input :value="adminUser.username" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input v-model="adminUser.password" type="password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="closeUpdatePwd">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :loading="loading">{{ loading ? '提交中 ...' : '确 定' }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import adminApi from '@/api/admin'
  import md5 from 'js-md5'
  export default {
    name: "admin-list",
    data() {
      return {
        loading: false
      }
    },
    props: {
      adminUser: {
        type: Object,
        defaulst: null
      }
    },
    methods: {
      onSubmit() {
        this.loading = true
        this.adminUser.password = md5(this.adminUser.password)
        adminApi.updatePassword(this.adminUser).then(res => {
          this.loading = false
          this.$message.success(res.msg)
          this.$emit('closeUpdatePwd')
          this.adminUser.password = null
        })
          .catch(res => {
            this.loading = false
          })
      },
      closeUpdatePwd() {
        this.$emit('closeUpdatePwd')
      },
    }
  }
</script>

<style scoped>

</style>
