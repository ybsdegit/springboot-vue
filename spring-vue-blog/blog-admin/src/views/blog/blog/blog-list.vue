<template>
  <div>
    <!--搜索-->
    <el-form :inline="true" :model="page" class="demo-form-inline">
      <el-form-item label="标题">
        <el-input v-model="page.params.blogTitle" placeholder="博客标题" clearable></el-input>
      </el-form-item>
      <el-form-item label="活动区域">
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
      <el-table-column prop="blogTitle" label="标题" width="100" show-overflow-tooltip/>
      <el-table-column prop="typeName" label="分类"/>
      <el-table-column prop="blogImage" label="图片">
        <template slot-scope="scope">
          <el-image
            style="width: 100%; height: 100px"
            :src="scope.row.blogImage"
            :preview-src-list="[scope.row.blogImage]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="blogGoods" label="点赞数" width="50" sortable="custom"/>
      <el-table-column prop="blogRead" label="阅读数" width="50" sortable="custom"/>
      <el-table-column prop="blogCollection" label="收藏数" width="50" sortable="custom"/>
      <el-table-column prop="blogComment" label="评论数" width="50" sortable="custom"/>
      <el-table-column prop="blogSource" label="文章来源"/>
      <el-table-column prop="createdTime" label="创建时间" sortable="custom"/>
      <el-table-column prop="updateTime" label="修改时间" sortable="custom"/>
      <el-table-column prop="blogRemark" label="备注" width="100" show-overflow-tooltip/>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-dropdown>
            <el-button type="primary" size="mini">操作
              <i class="el-icon-arrow-down el-icon--right"/>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                           @click="handleEdit(scope.row.blogId)">编辑
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                           @click="toRead(scope.row.blogId)">查看
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini" type="danger" plain
                  @click="handleDelete(scope.row.blogId)">删除
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
      <blog-add @closeAddDialog="closeAddDialog" @getByPage="getByPage"/>
    </el-dialog>

    <!--    修改弹窗-->
    <el-dialog
      title="修改" :visible.sync="updateDialog">
      <blog-update :blog="blog" @closeUpdateDialog="closeUpdateDialog" @getByPage="getByPage"/>
    </el-dialog>

    <!--    阅读弹窗-->
    <el-dialog
      title="阅读" :visible.sync="readDialog">
      <div v-html="blog.blogContent"></div>
    </el-dialog>

  </div>
</template>

<script>
  import blogApi from '@/api/blog'
  import BlogAdd from './blog-add'
  import BlogUpdate from './blog-update'

  export default {
    name: "blog-list",
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
        blog: {
          "blogId": "",
          "blogTitle": "",
          "blogImage": null,
          "blogContent": null,
          "blogGoods": 0,
          "blogRead": 0,
          "blogCollection": 0,
          "typeName": "",
          "blogRemark": null,
          "blogSource": null,
          "blogComment": 0,
          "createdTime": "",
          "updateTime": ""
        },
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
        blogApi.getByPage(this.page).then(res => {
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
        blogApi.get(id).then(res => {
          this.blog = res.data
          this.updateDialog = true
        })
      },
      toRead(id) {
        // 阅读
        blogApi.get(id).then(res => {
          this.blog = res.data
          this.readDialog = true
        /*this.$alert(res.data.blogContent, '查看', {
            dangerouslyUseHTMLString: true
          });*/
        })
      },
      handleDelete(id) {
        this.$confirm('是否删除该博客?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          blogApi.deleteById(id).then(res => {
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

