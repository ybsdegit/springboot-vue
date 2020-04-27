<template>
  <div class="login-background">
    <div class="login-container">
      <div class="login-title">欢迎进入Palson Blog</div>
      <a-menu v-model="current" mode="horizontal" @select="changeTab">
        <a-menu-item key="login">登录</a-menu-item>
        <a-menu-item key="register">注册</a-menu-item>
      </a-menu>
      <div class="content">
        <div v-show="login" class="login">
          <a-form :form="loginForm" @submit="loginSubmit">
            <a-form-item label="username">
              <a-input
                v-decorator="[
                  'username',
                  { rules: [{ required: true, message: '请输入账号!' }] },
                ]"
                placeholder="请输入账号"
              />
            </a-form-item>
            <a-form-item label="password" type="password">
              <a-input
                v-decorator="[
                  'password',
                  { rules: [{ required: true, message: '请输入密码!' }] },
                ]"
                placeholder="请输入密码"
              />
            </a-form-item>
            <a-form-item>
              <a-button type="primary" html-type="submit" block>
                登录
              </a-button>
            </a-form-item>
          </a-form>
        </div>
        <div v-show="register" class="register">
          <a-form :form="registerForm" @submit="registerSubmit">
            <a-form-item label="username">
              <a-input
                v-decorator="[
                  'username',
                  { rules: [{ required: true, message: '请输入账号!' }] },
                ]"
                placeholder="请输入账号"
              />
            </a-form-item>
            <a-form-item label="password" type="password">
              <a-input
                v-decorator="[
                  'password',
                  { rules: [{ required: true, message: '请输入密码!' }] },
                ]"
                placeholder="请输入密码"
              />
            </a-form-item>
            <a-form-item label="nickname">
              <a-input
                v-decorator="[
                  'nickname',
                  { rules: [{ required: true, message: '请输入昵称!' }] },
                ]"
                placeholder="请输入昵称"
              />
            </a-form-item>
            <a-form-item label="userEmail">
              <a-input
                v-decorator="[
                  'userEmail',
                  { rules: [{ required: true, message: '请输入邮箱!' }] },
                ]"
                placeholder="请输入邮箱"
              />
            </a-form-item>

            <a-form-item label="性别">
              <a-radio-group v-decorator="['sex']">
                <a-radio value="1">
                  男
                </a-radio>
                <a-radio value="2">
                  女
                </a-radio>
              </a-radio-group>
            </a-form-item>

            <a-form-item>
              <a-button type="primary" html-type="submit" block>
                注册
              </a-button>
            </a-form-item>
          </a-form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      current: ['login'],
      login: true,
      register: false,
      formLayout: 'horizontal',
      loginForm: this.$form.createForm(this, { name: 'coordinated' }),
      registerForm: this.$form.createForm(this, { name: 'coordinated' })

    }
  },
  methods: {
    changeTab(e) {
      console.log(e)
      const key = e.key
      if (key === 'login') {
        this.login = true
        this.register = false
      } else {
        this.login = false
        this.register = true
      }
    },
    loginSubmit(e) {
      e.preventDefault()
      this.loginForm.validateFields((err, values) => {
        if (!err) {
          console.log('登录参数 ', values)
          this.$router.push('/')
        }
      })
    },
    registerSubmit(e) {
      e.preventDefault()
      this.registerForm.validateFields((err, values) => {
        if (!err) {
          console.log('注册参数: ', values)
        }
      })
    }
  }
}
</script>
<style scoped>
.login-background {
  width: 100%;
  height: 100%;
  background-image: url('/login-back.jpg');
  position: fixed;
}
.login-container {
  background-color: #fff;
  width: 450px;
  min-height: 450px;
  margin: 150px auto;
  border: 1px solid #e5e5e5;
  padding: 50px 50px 0 50px;
  border-radius: 5px;
}
.login-title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 40px;
}
.ant-menu-item {
  width: 50%;
  text-align: center;
}

.content {
  margin-top: 20px;
}
</style>
