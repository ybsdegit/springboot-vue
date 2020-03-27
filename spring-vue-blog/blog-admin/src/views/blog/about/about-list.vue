<template>
  <div>
    <!--搜索-->
    <el-form :inline="true" :model="page" class="demo-form-inline">
      <el-form-item label="标题">
        <el-input v-model="page.params.aboutTitle" placeholder="标题" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getByPage" size="mini">查询</el-button>
      </el-form-item>
    </el-form>

    <el-divider/>
    <el-button type="primary" class="add-button" size="mini" @click="openAddDialog">添加</el-button>

    <!--    列表-->
    <el-table :data="page.list" border style="width: 100%" @sort-change="changeSort">
      <el-table-column prop="aboutId" label="编号" width="100" show-overflow-tooltip/>
      <el-table-column prop="aboutTitle" label="标题" width="100" show-overflow-tooltip/>
      <el-table-column prop="aboutRead" label="阅读数" width="70" sortable="custom"/>
      <el-table-column prop="createdTime" label="创建时间" sortable="custom"/>
      <el-table-column prop="updateTime" label="修改时间" sortable="custom"/>
      <el-table-column prop="enable" label="启用">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.enable === 1">启用</el-tag>
          <el-tag v-else type="info">未启用</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-dropdown>
            <el-button type="primary" size="mini">操作
              <i class="el-icon-arrow-down el-icon--right"/>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                           @click="handleEdit(scope.row.aboutId)">编辑
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                           @click="toRead(scope.row.aboutId)">查看
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button v-if="scope.row.enable === 0"
                           size="mini" type="success" plain
                           @click="toEnable(scope.row.aboutId)">启用
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button v-if="scope.row.enable === 1"
                           size="mini" type="warning" plain
                           @click="toDisEnable(scope.row.aboutId)">弃用
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini" type="danger" plain
                  @click="handleDelete(scope.row.aboutId)">删除
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
      <about-add @closeAddDialog="closeAddDialog" @getByPage="getByPage"/>
    </el-dialog>

    <!--    修改弹窗-->
    <el-dialog
      title="修改" :visible.sync="updateDialog">
      <about-update :about="about" @closeUpdateDialog="closeUpdateDialog" @getByPage="getByPage"/>
    </el-dialog>

    <!--    阅读弹窗-->
    <el-dialog
      title="阅读" :visible.sync="readDialog">
      <div v-html="about.aboutContent"></div>
    </el-dialog>

  </div>
</template>

<script>
  import aboutApi from '@/api/about'
  import AboutAdd from './about-add'
  import AboutUpdate from './about-update'

  export default {
    name: "about-list",
    components: {
      AboutAdd, AboutUpdate
    },
    data() {
      return {
        page: {
          currentPage: 1,
          pageSize: 10,
          totalPage: 0,
          totalCount: 0,
          params: {},
          list: [],
          sortColumn: '',
          sortMethod: 'asc'
        },
        about: {},
        typeList: null,
        addDialog: false, // 控制添加弹窗显示
        updateDialog: false,
        readDialog: false
      }
    },
    created() {
      this.getByPage();
      setTimeout(() => {
        this.typeList = this.$store.getters.typeList
      }, 200)
    },
    methods: {
      getByPage() {
        aboutApi.getByPage(this.page).then(res => {
          this.page = res.data
        })
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
      handleSizeChange(val) {
        this.page.pageSize = val
        this.getByPage()
      },
      handleCurrentChange(val) {
        this.page.currentPage = val
        this.getByPage()
      },
      handleEdit(id) {
        // 修改
        aboutApi.get(id).then(res => {
          this.about = res.data
          this.updateDialog = true
        })

      },
      handleDelete(id) {
        this.$confirm('是否删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          aboutApi.deleteById(id).then(res => {
            this.$message.success(res.msg)
            this.getByPage()
          })
        });
      },
      changeSort(e){
        console.log(e)
        if (e.order === null){
          return
        }
        this.page.sortColumn = e.prop
        this.page.sortMethod = e.order
        this.getByPage()
      },
      toRead(id) {
        // 阅读
        aboutApi.get(id).then(res => {
          this.about = res.data
          this.readDialog = true
        })
      },
      toEnable(id){
        aboutApi.enableById(id).then(res =>{
          this.$message.success(res.msg)
          this.getByPage()
        })
      },
      toDisEnable(id){
        this.$confirm('是否弃用?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          aboutApi.disableById(id).then(res =>{
            this.$message.success(res.msg)
            this.getByPage()
          })
        })
      },
    }
  }
</script>

