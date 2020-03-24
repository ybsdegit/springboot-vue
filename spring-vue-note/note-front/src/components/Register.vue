<template>
    <div>
        <img alt="Vue logo" src="../assets/logo.png">
        <h1>注册</h1>
        <el-form ref="form" :model="registerForm" label-width="80px">
            <el-form-item label="用户名">
                <el-input placeholder="请输入用户名" v-model="registerForm.username"></el-input>
            </el-form-item>

            <el-form-item label="密码">
                <el-input placeholder="请输入密码" v-model="registerForm.password" show-password></el-input>
            </el-form-item>

            <el-form-item label="手机号" prop="phone">
                <el-input placeholder="请输入手机号" v-model="registerForm.phone"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">提交</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data() {
            return {
                registerForm: {
                    username: '222',
                    password: '111',
                    phone: '18810911636',
                }
            }
        },
        methods: {
            onSubmit() {
                const _this = this
                this.axios.post('/register', {
                    username: this.registerForm.username,
                    password: this.registerForm.password,
                    phone: this.registerForm.phone
                })
                .then(function(response){
                    console.log(response)
                    if (response.data.status == 200){
                        _this.$message.success(response.data.msg);
                        _this.$router.push('/login')
                    }else{
                        _this.$message.error(response.data.msg);

                    }
                })
                .catch(function (response) {
                    console.log(response)
                    _this.$message.error(response.data);
                })
            }
        }
    }
</script>


<style scoped>

</style>