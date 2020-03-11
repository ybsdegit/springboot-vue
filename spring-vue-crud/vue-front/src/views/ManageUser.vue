<template>
    <div>
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="id"
                    label="编号"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="姓名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="password"
                    label="密码"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop=""
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
                    <el-button @click="deleteUser(scope.row)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="total"
                @current-change="page">
        </el-pagination>
    </div>

</template>

<script>
    export default {
        methods: {
            edit(row) {
                this.$router.push({
                    path: '/updateUser',
                    query: {
                        id: row.id
                    }
                })
                console.log(row.id);
            },

            deleteUser(row) {
                const _this = this
                axios.delete("http://localhost:8081/user/deleteById/" + row.id).then(function (resp) {
                    if (resp.status == 200){
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        window.location.reload()
                    }else {
                        _this.$message.error('删除失败');
                    }

                })
            },

            page(currentPage){
                const _this = this
                axios.get("http://localhost:8081/user/findAll/"+ (currentPage-1)+"/"+this.pageSize).then(function (resp) {
                    console.log(resp)
                    _this.tableData = resp.data.content
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.totalElements

                })
            }
        },

        data() {
            return {
                pageSize: 5,
                total: 10,
                tableData: []
            }
        },
        created() {
            const _this = this
            axios.get("http://localhost:8081/user/findAll/0/5").then(function (resp) {
                _this.tableData = resp.data.content
                _this.pageSize = resp.data.size
                _this.total = resp.data.totalElements

            })
        }
    }
</script>

<style scoped>

</style>