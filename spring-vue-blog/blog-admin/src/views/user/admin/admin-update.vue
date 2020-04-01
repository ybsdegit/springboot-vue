<template>
  <div>
    <el-form ref="form" :model="adminUser" label-width="80px" size="mini">
      <el-form-item label="账号">
        <el-input v-model="adminUser.username"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="adminUser.name"></el-input>
      </el-form-item>
      <el-form-item label="头像">
<!--        <el-input v-model="adminUser.header"></el-input>-->
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
      <el-form-item label="签名">
        <el-input v-model="adminUser.signature" type="textarea"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit" :loading="loading">{{ loading ? '提交中 ...' : '提交修改' }}</el-button>
        <el-button type="primary" @click="toUpdatePassword">修改密码</el-button>
      </el-form-item>


    </el-form>


    <!--    修改弹窗-->
    <el-dialog
      title="修改密码" :visible.sync="updateDialogPwd">
      <admin-update-pwd :admin-user="adminUser" @closeUpdatePwd="closeUpdatePwd"/>
    </el-dialog>
  </div>
</template>

<script>
  import adminApi from '@/api/admin'
  import {getToken} from '@/utils/auth'
  import AdminUpdatePwd from './admin-update-password'



  export default {
    name: "admin-update",
    components: {AdminUpdatePwd},
    data() {
      return {
        headers: { // 上传文件请求头
          Authorization: getToken(),
        },
        loading: false,
        updateDialogPwd: false, // 修改密码弹窗
        adminUser: {},
        imageUrl: '', // 上传图片回显
        uploadUrl: process.env.VUE_APP_UPLOAD_API, // 上传图片路径
      }
    },
    watch: {
      'adminUser.header': function (newVal, oldVal) {
        this.imageUrl = newVal
      },
    },
    created() {
      this.getAdmin()
    },
    methods: {
      getAdmin() {
        adminApi.getAdmin().then(res => {
          this.adminUser = res.data
        })
      },
      uploadSuccess(res, file) {
        this.$message.success(res.msg)
        this.imageUrl = res.data
        this.adminUser.header = res.data
      },
      toUpdatePassword() {
        this.updateDialogPwd = true
      },
      closeUpdatePwd() {
        this.updateDialogPwd = false
      },
      onSubmit() {
        this.loading = true
        adminApi.updateInfo(this.adminUser).then(res => {
          this.loading = false
          this.$message.success(res.msg)
        })
          .catch(res => {
            this.loading = false
          })
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
