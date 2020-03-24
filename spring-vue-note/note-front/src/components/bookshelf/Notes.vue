<template>
    <div style="">
        <el-card shadow="hover" v-for="(item,i) in notes" :key="i" class="box-card"
                 style="display: inline-block; width: 20%">
            <div slot="header" class="clearfix">
                <span>{{item.name}}</span>
                <el-tooltip transition="0s" class="item" effect="dark" content="编辑笔记内容" placement="top-start">
                    <el-button style="float: right; padding: 3px 0;margin-right: 6px" type="text">
                        <i class="el-icon-edit"></i>
                    </el-button>
                </el-tooltip>

                <el-tooltip transition="0s" class="item" effect="dark" content="查看笔记内容" placement="top-start">
                    <el-button style="float: right; padding: 3px 0; margin-right: 6px" type="text">
                        <i class="el-icon-more"></i>
                    </el-button>
                </el-tooltip>

                <el-tooltip transition="0s" class="item" effect="dark" content="编辑笔记标题与简介" placement="top-start">
                    <el-button style="float: right; padding: 3px 0; " type="text" @click="editInfo(item)">
                        <i class="el-icon-edit-outline"></i>
                    </el-button>
                </el-tooltip>

                <el-tooltip transition="0s" class="item" effect="dark" content="删除此笔记" placement="top-start">
                    <el-button style="float: right; padding: 3px 0;" type="text" @click="deleteNote(item.id)">
                        <i class="el-icon-delete"></i>
                    </el-button>
                </el-tooltip>
            </div>
            <div class="text item">
                {{item.abs}}
            </div>
        </el-card>

        <el-card shadow="hover" class="box-card" style="display: inline-block; width: 20%">
            <div slot="header" class="clearfix">
                <span>新增笔记</span>
            </div>
            <div class="text item" style="text-align: center">
                <i class="el-icon-circle-plus-outline" style="font-size: 40px; cursor: pointer"
                   @click="addNote"></i>
            </div>
        </el-card>

    </div>
</template>

<script>
    export default {
        name: "Notes",
        data() {
            return {
                notes: [],
            }
        },
        methods: {
            editInfo(note) {
                const noteInfo = {
                    id: note.id,
                    name: note.name,
                    abs: note.abs
                }
                this.$emit('editInfo', noteInfo)
            },
            addNote() {
                this.$emit('addNote')
            },
            deleteNote(id) {
                const _this = this
                this.axios.get('/note/delete/' + id)
                    .then(function (response) {
                        if (response.data.status == 200) {
                            _this.$message.success("删除成功")
                            _this.$emit('deleteInfo')
                        }else {
                            _this.$message.error(response.data);
                        }

                    })
                    .catch(function (response) {
                        _this.$message.error("删除失败")
                    })
            }
        }
    }
</script>

<style>
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 360px;
    }
</style>