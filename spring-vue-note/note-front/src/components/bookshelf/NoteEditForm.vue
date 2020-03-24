<template>
    <div>
        <el-dialog title="添加笔记本标题简介" :visible.sync="dialogFormVisible" :before-close="handleClose">
            <el-form :model="form">
                <el-form-item label="笔记名称" :label-width="formLabelWidth">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="笔记简介" :label-width="formLabelWidth">
                    <el-input type="textarea" :rows="3" v-model="form.abs" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible=false">取 消</el-button>
                <el-button type="primary" @click="updateNote">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "NoteEditForm",
        data() {
            return {
                isCreate: false, // 默认是修改
                dialogTableVisible: false,
                dialogFormVisible: false,
                form: {
                    id: '',
                    name: '',
                    abs: '',
                    category: ''
                },
                formLabelWidth: '120px'
            };
        },
        methods: {
            updateNote(){
                const _this = this
                var url = ''
                if(_this.isCreate){
                    // 如果是添加
                    url = '/add/note/info/'+this.form.category
                }else{
                    // 修改
                    url = '/update/note/'+this.form.id+'/info'
                }
                this.axios.post(url, this.form)
                .then(function (response) {
                    console.log(response)
                    if (response.data.status == 200) {
                        _this.dialogFormVisible = false
                        _this.$message.success(response.data.msg);
                        _this.$emit('updateInfo')
                    }else {
                        _this.$message.error(response.data);
                    }
                })
                .catch(function (response) {
                    _this.$message.error(response.data);
                })
            },
            deleteNote(){

            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
            },
        },
    };
</script>

<style scoped>

</style>