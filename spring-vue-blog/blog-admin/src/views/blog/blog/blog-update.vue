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
        <el-upload
          class="avatar-uploader"
          :action="uploadUrl"
          :headers="headers"
          :on-success="uploadSuccess"
          :show-file-list="false">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="内容">
        <tinymce v-model="blog.blogContent"/>
      </el-form-item>
      <el-form-item label="来源">
        <el-input v-model="blog.blogSource"></el-input>
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
  import Tinymce from '@/components/Tinymce'
  import { getToken } from '@/utils/auth'


  export default {
    name: 'blog-update',
    components:{Tinymce},
    props: {
      blog: {
        type: Object,
        default: null,
      }
    },
    watch: {
      'blog.blogImage': function(newVal, oldVal) {
        this.imageUrl = newVal
      },
    },
    data() {
      return {
        headers: { // 上传文件请求头
          Authorization: getToken(),
        },
        imageUrl: this.blog.blogImage, // 上传图片回显
        uploadUrl: process.env.VUE_APP_UPLOAD_API, // 上传图片路径
        typeList: this.$store.getters.typeList,
        loading: false,
      }
    },
    methods: {
      onSubmit() {
        console.log(this.blog)
        this.loading = true
        blogApi.update(this.blog).then(res => {
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
      },
      uploadSuccess(res, file) {
        this.$message.success(res.msg)
        this.imageUrl = res.data
        this.blog.blogImage = res.data
      },
    }
  }
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
