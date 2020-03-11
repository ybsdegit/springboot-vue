<template>
    <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="编号" prop="id">
            <el-input v-model="ruleForm.id" readOnly></el-input>
        </el-form-item>

        <el-form-item label="用户名" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
    </el-form>

</template>
<script>
    export default {
        data() {
            return {
                ruleForm: {
                    id : 1,
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'change' },
                        { min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put("http://localhost:8081/user/updateUser", this.ruleForm).then(function (resp) {
                            console.log(resp)
                            if (resp.data == "success"){
                                _this.$message({
                                    message: '修改用户成功',
                                    type: 'success'
                                });
                                _this.$router.push("/manageUser")
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        _this.$message.error('修改用户失败');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            const id = this.$route.query.id
            const _this = this
            axios.get('http://localhost:8081/user/findById/' + id).then(function (resp) {
                _this.ruleForm = resp.data
            })
        }
    }
</script>