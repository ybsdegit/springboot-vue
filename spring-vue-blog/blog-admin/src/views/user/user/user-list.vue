<template>
  <div>

    <!--搜索-->
    <el-form :inline="true" :model="page" class="demo-form-inline">
      <el-form-item label="账号">
        <el-input v-model="page.params.username" placeholder="账号" clearable></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="page.params.name" placeholder="姓名" clearable></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="page.params.nickname" placeholder="昵称" clearable></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="page.params.sex" placeholder="性别" clearable filterable>
          <el-option label="男" :value="1"></el-option>
          <el-option label="女" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="page.params.userEmail" placeholder="邮箱" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getByPage" size="mini">查询</el-button>
      </el-form-item>
    </el-form>

    <el-divider/>
    <el-button type="primary" class="add-button" size="mini" @click="openAddDialog">添加</el-button>
    <el-button type="primary" class="add-button" size="mini" @click="resetPwd">重置密码</el-button>

    <!--列表-->
    <el-table
      @selection-change="handleSelectionChange"
      :data="page.list" border style="width: 100%" @sort-change="changeSort">
      <el-table-column
        type="selection"
        width="55"/>
      <el-table-column prop="userId" label="用户编号"/>
      <el-table-column prop="username" label="账号"/>
      <el-table-column prop="header" label="头像">
        <template slot-scope="scope">
          <el-image
            style="width: 100%; height: 100px"
            :src="scope.row.header"
            :preview-src-list="[scope.row.header]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="姓名"/>
      <el-table-column prop="sex" label="性别" sortable="custom">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.sex === 1" type="success">男</el-tag>
          <el-tag v-else type="danger">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="nickname" label="昵称"/>
      <el-table-column prop="userEmail" label="邮箱"/>
      <el-table-column prop="createdTime" label="创建时间" sortable="custom"/>
      <el-table-column prop="updateTime" label="修改时间" sortable="custom"/>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-dropdown>
            <el-button type="primary" size="mini">操作
              <i class="el-icon-arrow-down el-icon--right"/>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                           @click="handleEdit(scope.row.userId)">编辑
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini" type="danger" plain
                  @click="handleDelete(scope.row.userId)">删除
                </el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <!--    分页-->
    <el-pagination class="pagination"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="page.currentPage"
                   :page-sizes="[5, 10, 30, 50]"
                   :page-size="page.pageSize"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="page.totalCount">
    </el-pagination>

    <!--    添加弹窗-->
    <el-dialog
      title="添加" :visible.sync="addDialog">
      <user-add @closeAddDialog="closeAddDialog" @getByPage="getByPage"/>
    </el-dialog>

    <!--    修改弹窗-->
    <el-dialog
      title="修改" :visible.sync="updateDialog">
      <user-update :user="user" @closeUpdateDialog="closeUpdateDialog" @getByPage="getByPage"/>
    </el-dialog>
  </div>
</template>

<script>
  import userApi from '@/api/myUser'
  import userAdd from './user-add'
  import userUpdate from './user-update'

  export default {
    name: "user-list",
    components: {
      userAdd, userUpdate
    },
    data() {
      return {
        page: {
          currentPage: 1,
          pageSize: 5,
          totalPage: 0,
          totalCount: 0,
          params: {},
          list: [],
          sortColumn: '',
          sortMethod: 'asc'
        },
        selectUsers: [],  // 被选中的用户
        user: {},
        addDialog: false, // 控制添加弹窗显示
        updateDialog: false,
      }
    },
    methods: {
      getByPage() {
        userApi.getByPage(this.page).then(res => {
          this.page = res.data
        })
      },
      handleEdit(id) {
        // 修改
        userApi.get(id).then(res => {
          this.user = res.data
          this.updateDialog = true
        })
      },

      handleDelete(id) {
        this.$confirm('是否删除该用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          userApi.deleteById(id).then(res => {
            this.$message.success(res.msg)
            this.getByPage()
          })
        });
      },
      changeSort(e) {
        console.log(e)
        if (e.order === null) {
          return
        }
        this.page.sortColumn = e.prop
        this.page.sortMethod = e.order
        this.getByPage()
      },
      handleSizeChange(val) {
        this.page.pageSize = val
        this.getByPage()
      },
      handleCurrentChange(val) {
        this.page.currentPage = val
        this.getByPage()
      },
      openAddDialog() {
        // 打开添加弹窗
        this.addDialog = true
      },
      closeAddDialog() {
        // 关闭添加弹窗
        this.addDialog = false
      },
      closeUpdateDialog() {
        // 关闭修改弹窗
        this.updateDialog = false
      },
      handleSelectionChange(val) {
        this.selectUsers = val
      },
      resetPwd() {
        const userIds = []
        this.selectUsers.forEach(e => {
          userIds.push(e.userId)
        })
        console.log(userIds)
        userApi.resetPwd(userIds).then(res => {
          this.$message.success(res.msg)
        })
      }
    },
    created() {
      this.getByPage()
    },
  }
</script>

<style scoped>

</style>
