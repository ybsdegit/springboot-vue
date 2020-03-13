<template>
    <portal-template>
        <div id="login">
            <el-form label-position="top" label-width="80px" :model="user"
                     :rules="rules" ref="ruleForm">

                <el-form-item label="用户名" prop="username">
                    <el-input v-model="user.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input placeholder="请输入密码" v-model="user.password" show-password></el-input>
                </el-form-item>
                <el-button class="submit-btn" type="primary" @click="login('ruleForm')">登录</el-button>
            </el-form>
            <router-link to="/register">
                <el-button type="text" icon="el-icon-edit">去注册</el-button>
            </router-link>

        </div>
    </portal-template>
</template>

<script>
    import PortalTemplate from "./PortalTemplate";

    export default {
        name: 'login',

        components: {
            PortalTemplate
        },

        methods: {

            login(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // this.axios.post('/api/login', qs.stringify(this.user))
                        // .then(function (response) {
                        //     console.log(response)
                        //     if (response.data.code == "200"){
                        //         _this.$store.dispatch('changeMyUser', {username: _this.user.username})
                        //         _this.$message.success(response.data.message);
                        //         _this.$router.push("/")
                        //     }else {
                        //         _this.$message.error(response.data.message);
                        //         return false;
                        //     }
                        // })
                        // .catch(function (error) {
                        //     _this.$message.error("系统错误")
                        //
                        // })
                        _this.$store.dispatch('login', {username: _this.user.username, password: _this.user.password})
                            .then((response) => {
                                _this.$message.success(response.data.message);
                                _this.$router.push("/")
                            })
                            .catch((response) =>{
                            _this.$message.error(response.data.message);
                            })
                    } else {
                        console.log('error submit!!');
                        this.$message.error("用户名密码格式不对")
                        return false;
                    }
                });
            },
        },
        data() {
            return {
                user: {
                    username: 'root',
                    password: 'mima',
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
    }
</script>

<style lang="css">
    @import "../assets/css/portal-template.css";
</style>
