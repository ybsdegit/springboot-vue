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
    name: 'about-update',
    components:{Tinymce},
    props: {
      about: {
        type: Object,
        default: null,
      }
    },
    data() {
      return {
        loading: false,
      }
    },
    methods: {
      onSubmit() {
        console.log(this.about)
        this.loading = true
        aboutApi.update(this.about).then(res => {
          this.loading = false
          this.$message.success(res.msg)
          this.$emit('closeUpdateDialog')
          this.$emit('getByPage')
        })
          .catch(res => {
            console.log(res)
            this.loading = false
          })
      },
      cancelForm() {
        this.$emit('closeUpdateDialog')
      }
    }
  }
</script>

