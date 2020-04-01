<template>
  <div>

    <!--搜索-->
    <el-form :inline="true" :model="page" class="demo-form-inline">
      <el-form-item label="请求地址">
        <el-input v-model="page.params.logUrl" placeholder="请求地址" clearable></el-input>
      </el-form-item>
      <el-form-item label="请求IP">
        <el-input v-model="page.params.logIp" placeholder="请求IP" clearable></el-input>
      </el-form-item>

      <el-form-item label="请求状态">
        <el-select v-model="page.params.logStatus" placeholder="请求状态" clearable filterable>
          <el-option label="正常" :value="1"></el-option>
          <el-option label="异常" :value="2"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="请求方式">
        <el-select v-model="page.params.logMethod" placeholder="请求方式" clearable filterable>
          <el-option label="GET" value="GET"></el-option>
          <el-option label="POST" value="POST"></el-option>
          <el-option label="PUT" value="PUT"></el-option>
          <el-option label="DELETE" value="DELETE"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getByPage" size="mini">查询</el-button>
      </el-form-item>
    </el-form>

    <el-divider/>
    <el-button type="danger" class="add-button" size="mini" @click="deleteByIds">批量删除</el-button>

    <!--列表-->
    <el-table
      @selection-change="handleSelectionChange"
      :data="page.list" border style="width: 100%" @sort-change="changeSort">
      <el-table-column
        type="selection"
        width="55"/>
      <el-table-column prop="logId" label="编号"/>
      <el-table-column prop="logUrl" label="请求地址" sortable="custom"/>
      <el-table-column prop="logParams" label="参数" show-overflow-tooltip />
      <el-table-column prop="logStatus" label="状态" sortable="custom">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.logStatus === 1" type="success">正常</el-tag>
          <el-tag v-else type="danger">异常</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="logMethod" label="请求方式" sortable="custom"/>
      <el-table-column prop="logTime" label="响应时间（毫秒）" sortable="custom"/>
      <el-table-column prop="logIp" label="请求IP" />
      <el-table-column prop="logResult" label="返回值" width="100" show-overflow-tooltip />
      <el-table-column prop="createdTime" label="创建时间" sortable="custom"/>
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

  </div>
</template>

<script>
  import logApi from '@/api/log'

  export default {
    name: "log-list",
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
          sortMethod: 'desc'
        },
        selectLogs: [],  // 被选中的用户
        log: {},
      }
    },
    methods: {
      getByPage() {
        logApi.getByPage(this.page).then(res => {
          this.page = res.data
        })
      },
      handleDelete(id) {
        this.$confirm('是否删除该用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          logApi.deleteById(id).then(res => {
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
      handleSelectionChange(val) {
        this.selectLogs = val
      },
      deleteByIds() {
        this.$confirm('删除之后无法恢复！是否删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          const logIds = []
          this.selectLogs.forEach(e => {
            logIds.push(e.logId)
          })
          logApi.deleteByIds(logIds).then(res => {
            this.$message.success(res.msg)
            this.getByPage()
          })
        });


      }
    },
    created() {
      this.getByPage()
    },
  }

</script>

<style scoped>

</style>
