<template>
  <div>
    <el-form :model="link" ref="form" label-width="80px">
      <el-form-item label="链接名称" label-width="20%">
        <el-input v-model="link.linkName"></el-input>
      </el-form-item>
      <el-form-item label="链接地址" label-width="20%">
        <el-input v-model="link.linkUrl"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="cancelForm">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :loading="loading">{{ loading ? '提交中 ...' : '确 定' }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import linkApi from '@/api/link.js'

  export default {
  name: 'link-update',
  props: {
    link: Object,
    default: null
  },
  data() {
    return {
      loading: false,
    }
  },
  methods: {
    onSubmit() {
      this.loading = true
      linkApi.update(this.type).then(res => {
        this.loading = false
        this.$message.success(res.msg)
        this.$emit('closeUpdateDialog')
        this.$emit('getLinkList')
      })
    },
    cancelForm() {
      this.$emit('closeUpdateDialog')
    }
  }
}
</script>

<style scoped>

</style>
