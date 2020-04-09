<template>
  <div>
    <!--搜索-->
    <el-form :inline="true" :model="page" class="demo-form-inline">
      <el-form-item label="标题">
        <el-input v-model="page.params.musicTitle" placeholder="博客标题" clearable></el-input>
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="page.params.typeId" placeholder="分类" clearable filterable>
          <el-option v-for="item in typeList" :key="item.typeId" :label="item.typeName"
                     :value="item.typeId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getByPage" size="mini">查询</el-button>
      </el-form-item>
    </el-form>

    <el-divider/>
    <el-button type="primary" class="add-button" size="mini" @click="openAddDialog">添加</el-button>

    <!--    列表-->
    <el-table :data="page.list" border style="width: 100%" @sort-change="changeSort">
      <el-table-column prop="musicTitle" label="标题" width="100" show-overflow-tooltip/>
      <el-table-column prop="typeName" label="分类"/>
      <el-table-column prop="musicImage" label="图片">
        <template slot-scope="scope">
          <el-image
            style="width: 100%; height: 100px"
            :src="scope.row.musicImage"
            :preview-src-list="[scope.row.musicImage]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="musicGoods" label="点赞数" width="50" sortable="custom"/>
      <el-table-column prop="musicRead" label="阅读数" width="50" sortable="custom"/>
      <el-table-column prop="musicCollection" label="收藏数" width="50" sortable="custom"/>
      <el-table-column prop="musicComment" label="评论数" width="50" sortable="custom"/>
      <el-table-column prop="musicSource" label="文章来源"/>
      <el-table-column prop="createdTime" label="创建时间" sortable="custom"/>
      <el-table-column prop="updateTime" label="修改时间" sortable="custom"/>
      <el-table-column prop="musicRemark" label="备注" width="100" show-overflow-tooltip/>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-dropdown>
            <el-button type="primary" size="mini">操作
              <i class="el-icon-arrow-down el-icon--right"/>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                           @click="handleEdit(scope.row.musicId)">编辑
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini" type="danger" plain
                  @click="handleDelete(scope.row.musicId)">删除
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
      <music-add @closeAddDialog="closeAddDialog" @getByPage="getByPage"/>
    </el-dialog>

    <!--    修改弹窗-->
    <el-dialog
      title="修改" :visible.sync="updateDialog">
      <music-update :music="music" @closeUpdateDialog="closeUpdateDialog" @getByPage="getByPage"/>
    </el-dialog>


  </div>
</template>

<script>
  import musicApi from '@/api/music'
  import BlogAdd from './music-add'
  import BlogUpdate from './music-update'

  export default {
    name: "music-list",
    components: {
      BlogAdd, BlogUpdate
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
        music: {
          "musicId": "",
          "musicTitle": "",
          "musicImage": null,
          "musicContent": null,
          "musicGoods": 0,
          "musicRead": 0,
          "musicCollection": 0,
          "typeName": "",
          "musicRemark": null,
          "musicSource": null,
          "musicComment": 0,
          "createdTime": "",
          "updateTime": ""
        },
        addDialog: false, // 控制添加弹窗显示
        updateDialog: false,
      }
    },
    created() {
      this.getByPage();
    },
    methods: {
      getByPage() {
        musicApi.getByPage(this.page).then(res => {
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
        musicApi.get(id).then(res => {
          this.music = res.data
          this.updateDialog = true
        })
      },
      handleDelete(id) {
        this.$confirm('是否删除该博客?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          musicApi.deleteById(id).then(res => {
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
    }
  }
</script>

