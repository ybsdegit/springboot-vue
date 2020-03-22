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
                <el-form-item>
                    <el-button type="primary" @click="onSubmit('ruleForm')">登录</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                registerForm: {
                    username: '222',
                    password: '111',
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
                                console.log(response)
                                if (response.data.status == 200) {
                                    _this.$message.success(response.data.msg);
                                    _this.$store.commit('login', response.data.data)
                                    _this.$router.push('/')
                                } else {
                                    _this.$message.error(response.data.msg);
                                }
                            })
                            .catch(function (response) {
                                console.log(response)
                                _this.$message.error(response.data);
                            })
                    }else{
                        console.log('error submit!!');
                        this.$message.error("用户名密码格式不对")
                        return false;
                    }
                }))

            }
        }
    }
</script>


<style scoped>

</style>