<template>
  <div>
    <div class="info-container">
      <div class="header-container">
        <div class="update-img-container">
          <img src="@/assets/header.gif" class="user-header">
          <a href="javascript:void(0);" @click="openHeaderUploadDialog">点我上传</a>
          <div class="meta-container">
            <div class="user-id">ID：1234234 评论：20</div>
            <div class="user-collection">收藏</div>
            <a-divider />
            <div class="info-form">
              <a-form :form="infoForm" @submit="infoSubmit">
                <a-form-item label="密码" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                  <a-button type="primary" @click="openUpdatePwdDialog">点我修改</a-button>
                </a-form-item>
                <a-form-item label="账号" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                  <a-input
                    v-decorator="[
                      'username',
                      { rules: [{ required: true, message: '请输入账号!' }] }
                    ]"
                    placeholder="请输入账号"
                  />
                </a-form-item>
                <a-form-item label="昵称" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                  <a-input
                    v-decorator="[
                      'nickname',
                      { rules: [{ required: true, message: '请输入昵称!' }] }
                    ]"
                    placeholder="请输入昵称"
                  />
                </a-form-item>
                <a-form-item label="邮箱" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                  <a-input
                    v-decorator="[
                      'userEmail',
                      { rules: [{ required: true, message: '请输入邮箱!' }] }
                    ]"
                    placeholder="请输入邮箱"
                  />
                </a-form-item>
                <a-form-item label="性别" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                  <a-radio-group v-decorator="['sex']">
                    <a-radio :value="1">男</a-radio>
                    <a-radio :value="2">女</a-radio>
                  </a-radio-group>
                </a-form-item>
                <a-form-item>
                  <a-button type="primary" html-type="submit" block>修改</a-button>
                </a-form-item>
              </a-form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <a-modal
      title="修改密码"
      :visible="visible"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <a-form :form="passwordForm">
        <a-form-item label="密码" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
          <a-input
            v-decorator="[
              'password',
              { rules: [{ required: true, message: '请输入密码!' }] }
            ]"
            type="password"
            placeholder="请输入密码"
          />
        </a-form-item>
      </a-form>
    </a-modal>

    <a-modal
      title="修改头像"
      :visible="uploadDialog"
      @cancel="uploadCancel"
    >
      <a-upload-dragger
        name="file"
        :multiple="true"
        @change="handleChange"
      >
        <!-- :action="uploadUrl"
        :headers="headers" -->
        <p class="ant-upload-drag-icon">
          <a-icon type="inbox" />
        </p>
        <p class="ant-upload-text">点击上传或拖拽到这里</p>
      </a-upload-dragger>
    </a-modal>

  </div>
</template>

<script>
export default {
  data() {
    return {
      infoForm: this.$form.createForm(this),
      passwordForm: this.$form.createForm(this),
      uploadUrl: 'process.env.VUE_APP_UPLOAD_URL', // 上传图片路径
      headers: { // 上传文件的请求头
        Authorization: 'getToken()'
      },
      uploadDialog: false, // 控制修改头像弹窗显示
      visible: false // 控制修改密码弹窗展示
    }
  },
  methods: {
    infoSubmit(e) {
      console.log(e)
    },
    openUpdatePwdDialog() {
      // 打开修改密码弹窗
      this.visible = true
    },
    handleOk(e) { // 提交
      this.visible = false
      console.log(e)
      console.log(this.passwordForm)
    },
    handleCancel(e) {
      // eslint-disable-next-line handle-callback-err
      this.passwordForm.validateFields((err, values) => {
        console.log(values)
      })
      this.visible = false
    },
    openHeaderUploadDialog() {
      // 打开头像上传弹窗
      this.uploadDialog = true
    },
    uploadCancel(e) {
      this.uploadDialog = false
    },
    handleChange(info) {
      // 文件上传触发
      const status = info.file.status
      if (status === 'done') {
        const res = info.file.response
        this.$message.success(res.msg)
        this.user.header = res.data
        // userApi.update(this.user).then(res => {
        //   this.$store.commit('SET_USER', this.user)
        // })
        this.uploadDialog = false
      }
    }
  }
}
</script>

<style scoped>
.user-header {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: 3px solid #e5e5e5;
}

.header-container {
  display: flex;
  flex-direction: row;
}

.meta-container {
  display: flex;
  flex-direction: column;
  margin-left: 20px;
  width: 450px;
}

.user-id {
  color: #b39999;
  font-size: 18px;
}

.user-collection {
  line-height: 40px;
  font-size: 16px;
}

.update-img-container {
  display: flex;
  flex-direction: column;
}

.update-img-container a {
  text-align: center;
  margin-top: 20px;
}
</style>
