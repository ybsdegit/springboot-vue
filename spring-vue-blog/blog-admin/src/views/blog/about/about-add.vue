<template>
  <div>
    <el-form ref="form" :model="about" label-width="80px" size="mini">
      <el-form-item label="标题">
        <el-input v-model="about.aboutTitle"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <tinymce v-model="about.aboutContent"/>
      </el-form-item>
      <el-form-item>
        <el-button @click="cancelForm">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :loading="loading">{{ loading ? '提交中 ...' : '确 定' }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import aboutApi from '@/api/about'
  import Tinymce from '@/components/Tinymce'

  export default {
    name: 'about-add',
    components:{Tinymce},
    data() {
      return {
        about: {},
        loading: false,
      }
    },
    methods: {
      onSubmit() {
        this.loading = true
        aboutApi.save(this.about).then(res => {
          this.about = {}
          this.loading = false
          this.$message.success(res.msg)
          this.$emit('closeAddDialog')
          this.$emit('getByPage')
        })
          .catch(res => {
            console.log(res)
            this.loading = false
          })
      },
      cancelForm() {
        this.$emit('closeAddDialog')
      },
    }
  }
</script>

