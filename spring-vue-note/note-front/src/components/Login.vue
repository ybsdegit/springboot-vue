<template>
        <div id="login">
            <img alt="Vue logo" src="../assets/logo.png">
            <h1>登录</h1>
            <el-form :model="registerForm" label-width="80px"
                     :rules="rules" ref="ruleForm">
                <el-form-item label="用户名" prop="username">
                    <el-input placeholder="请输入用户名" v-model="registerForm.username"></el-input>
                </el-form-item>

                <el-form-item label="密码" prop="password">
                    <el-input placeholder="请输入密码" v-model="registerForm.password" show-password></el-input>
                </el-form-item>

                <el-form-item label="手机号" prop="phone">
                    <el-input placeholder="请输入手机号" v-model="registerForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="验证码" prop="code">
                    <el-input placeholder="请输入短信验证码" v-model="registerForm.code"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onSubmit('ruleForm')">登录</el-button>
                    <el-button @click="sendSms(registerForm.phone)">发送验证码</el-button>
                </el-form-item>
            </el-form>
        </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            let checkCode = (rule, value, callback) => {
                if (value == this.registerForm.checkCode) {
                    callback();
                } else {
                    callback(new Error('验证码错误'));
                }
            };
            return {
                registerForm: {
                    username: '222',
                    password: '111',
                    phone: '18810911636',
                    code: '',
                    checkCode: '0000'
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}

                    ],
                    phone: [
                        { required: true, message: '请输入手机号'},
                        { min:11, max:11, message: '手机号长度在11个数字', trigger: 'blur'},
                    ],
                    code: [
                        { required: true, message: '请输入短信验证码'},
                        { min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'},
                        { validator:checkCode, trigger: 'blur' }

                    ]
                }
            }
        },
        methods: {
            onSubmit(formName) {
                this.$refs[formName].validate((valid => {
                    if (valid){
                        const _this = this
                        this.axios.post('/login', {
                            username: this.registerForm.username,
                            password: this.registerForm.password,
                        })
                            .then(function (response) {
                                if (response.data.status == 200) {
                                    _this.$message.success(response.data.msg);
                                    _this.$store.commit('login', response.data.data)
                                    _this.$router.push('/')
                                } else {
                                    _this.$message.error(response.data.msg);
                                }
                            })
                            .catch(function (response) {
                                _this.$message.error(response.data);
                            })
                    }else{
                        this.$message.error("用户名码或验证码输入错误")
                        return false;
                    }
                }))

            },
            sendSms(phone){
                const _this = this
                const url = '/sendSms/'+_this.registerForm.phone
                this.axios.get('/sendSms/'+_this.registerForm.phone)
                .then(function (response) {
                    if (response.data.status == 200){
                        _this.$message.success(response.data.msg);
                        _this.registerForm.checkCode = response.data.data
                    }else {
                        _this.$message.error(response.data.msg);
                    }
                })

            },
        }
    }
</script>


<style scoped>

</style>