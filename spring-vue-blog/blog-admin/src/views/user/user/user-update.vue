<template>
  <div>
    <el-form ref="user" :model="user" label-width="80px" size="mini">
      <el-form-item label="账号">
        <el-input v-model="user.username" placeholder="账号" clearable></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="user.name" placeholder="姓名" clearable></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="user.nickname" placeholder="昵称" clearable></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio v-model="user.sex" :label="1">男</el-radio>
        <el-radio v-model="user.sex" :label="2">女</el-radio>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="user.userEmail" placeholder="邮箱" clearable></el-input>
      </el-form-item>
      <el-form-item label="头像">
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
      <el-form-item>
        <el-button @click="cancelForm">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :loading="loading">{{ loading ? '提交中 ...' : '确 定' }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import userApi from '@/api/myUser'
  import {getToken} from '@/utils/auth'

  export default {
    name: 'user-add',
    props: {
      user: {
        type: Object,
        default: null
      },
    },
    data() {
      return {
        headers: { // 上传文件请求头
          Authorization: getToken(),
        },
        imageUrl: this.user.header, // 上传图片回显
        uploadUrl: process.env.VUE_APP_UPLOAD_API, // 上传图片路径
        loading: false,
      }
    },
    watch: {
      'user.header': function (newVal, oldVal) {
        this.imageUrl = newVal
      },
    },
    methods: {
      onSubmit() {
        this.loading = true
        userApi.update(this.user).then(res => {
          this.loading = false
          this.$message.success(res.msg)
          this.$emit('getByPage')
          this.$emit('closeUpdateDialog')
        })
          .catch(res => {
            this.loading = false
          })
      },
      cancelForm() {
        this.$emit('closeUpdateDialog')
      },
      uploadSuccess(res, file) {
        this.$message.success(res.msg)
        this.imageUrl = res.data
        this.user.header = res.data
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
