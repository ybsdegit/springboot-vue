<template>
  <div>
    <el-form ref="form" :model="blog" label-width="80px" size="mini">
      <el-form-item label="标题">
        <el-input v-model="blog.blogTitle"></el-input>
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="blog.blogType" clearable filterable placeholder="请选择" style="width: 100%">
          <el-option
            v-for="type in typeList"
            :key="type.typeId"
            :label="type.typeName"
            :value="type.typeId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="封面">
        <el-input v-model="blog.blogImage"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="blog.blogContent"></el-input>
      </el-form-item>
      <el-form-item label="备注" >
        <el-input v-model="blog.blogRemark" type="textarea"></el-input>
      </el-form-item>


      <el-form-item>
        <el-button @click="cancelForm">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :loading="loading">{{ loading ? '提交中 ...' : '确 定' }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import blogApi from '@/api/blog'

  export default {
    name: 'blog-add',
    data() {
      return {
        blog: {
          "blogId": "",
          "blogTitle": "",
          "blogImage": null,
          "blogContent": null,
          "blogType": "",
          "blogSource": null,
          "blogRemark": null,
        },
        typeList: this.$store.getters.typeList,
        loading: false,
      }
    },
    methods: {
      onSubmit() {
        this.loading = true
        blogApi.save(this.blog).then(res => {
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
      }
    }
  }
</script>

<style scoped>

</style>
