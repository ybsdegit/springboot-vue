<template>
  <div>
    <el-form :model="type" ref="form" label-width="80px">
      <el-form-item label="分类名" label-width="20%">
        <el-input v-model="type.typeName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="cancelForm">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :loading="loading">{{ loading ? '提交中 ...' : '确 定' }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import typeApi from '@/api/type.js'

  export default {
    name: 'type-list',
    data() {
      return {
        type: {},
        loading: false,
      }
    },
    methods: {
      onSubmit() {
        this.loading = true
        typeApi.save(this.type).then(res => {
          this.loading = false
          if (res.code != 20000){
            this.$message.error(res.msg)
          }
          this.$message.success(res.msg)
          this.$emit('closeAddDra')
          this.$emit('getTypeList')
        })
          .catch(res => {
            console.log(res)
            this.loading = false
          })
      },
      cancelForm() {
        this.$emit('closeAddDra')
      }
    }
  }
</script>

<style scoped>

</style>
