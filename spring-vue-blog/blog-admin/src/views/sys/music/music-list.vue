<template>
  <div>
    <!--搜索-->
    <el-form :inline="true" :model="page" class="demo-form-inline">
      <el-form-item label="歌曲名">
        <el-input v-model="page.params.name" placeholder="博客标题" clearable></el-input>
      </el-form-item>
      <el-form-item label="歌手">
        <el-input v-model="page.params.artist" placeholder="博客标题" clearable></el-input>
      </el-form-item>
      <el-form-item label="启用">
        <el-select v-model="page.params.enabled" placeholder="分类" clearable filterable>
          <el-option label="启用" :value="1"/>
          <el-option label="未启用" :value="0"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getByPage" size="mini">查询</el-button>
      </el-form-item>
    </el-form>

    <el-divider/>
    <el-button type="primary" class="add-button" size="mini" @click="openAddDialog">添加</el-button>
    <audio :src="musicUrl" :autoplay="autoplay" controls="controls"/>

    <!--    列表-->
    <el-table :data="page.list" border style="width: 100%" @sort-change="changeSort">
      <el-table-column prop="id" label="编号" />
      <el-table-column prop="name" label="歌曲名" width="100" show-overflow-tooltip/>
      <el-table-column prop="artist" label="歌手" sortable="custom"/>
      <el-table-column prop="cover" label="封面">
        <template slot-scope="scope">
          <el-image
            style="width: 100%; height: 100px"
            :src="scope.row.cover"
            :preview-src-list="[scope.row.cover]">
          </el-image>
        </template>
      </el-table-column>

      <el-table-column prop="createdTime" label="创建时间" sortable="custom"/>
<!--      <el-table-column prop="updateTime" label="修改时间" sortable="custom"/>-->
      <el-table-column prop="enabled" label="启用" sortable="custom">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.enabled === 1">启用</el-tag>
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
                           @click="handleEdit(scope.row.id)">编辑
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                           @click="readLrc(scope.row.id)">查看歌词
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                           @click="toHear(scope.row.url)">试听
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button v-if="scope.row.enabled === 0"
                           size="mini" type="success" plain
                           @click="toEnable(scope.row.id)">启用
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button v-if="scope.row.enabled === 1"
                           size="mini" type="warning" plain
                           @click="toDisEnable(scope.row.id)">弃用
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini" type="danger" plain
                  @click="handleDelete(scope.row.id)">删除
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
    <!--    歌词弹窗-->
    <el-dialog
      title="修改" :visible.sync="lrcDialog">
      <div v-html="music.lrc"></div>
    </el-dialog>



  </div>
</template>

<script>
  import musicApi from '@/api/music'
  import musicAdd from './music-add'
  import musicUpdate from './music-update'

  export default {
    name: "music-list",
    components: {
      musicAdd, musicUpdate
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
        },
        addDialog: false, // 控制添加弹窗显示
        updateDialog: false,
        lrcDialog: false,
        musicUrl: '',
        autoplay: false,
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
      toEnable(id){
        musicApi.enableById(id).then(res =>{
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
          musicApi.disableById(id).then(res =>{
            this.$message.success(res.msg)
            this.getByPage()
          })
        })
      },
      readLrc(id){
        musicApi.get(id).then(res => {
          this.music = res.data
          this.lrcDialog = true
        })
      },
      toHear(url){
        this.musicUrl = url
        this.autoplay = true
      }
    }
  }
</script>

