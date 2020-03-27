<template>
  <div>

    <el-button type="primary" class="add-button" size="mini" @click="openAddDialog">添加</el-button>

    <el-table :data="page.list" border style="width: 100%">
      <el-table-column prop="blogTitle" label="标题" width="100" show-overflow-tooltip />
      <el-table-column prop="typeName" label="分类"/>
      <el-table-column prop="blogImage" label="图片"/>
      <el-table-column prop="blogGoods" label="点赞数" width="50"/>
      <el-table-column prop="blogRead" label="阅读数"width="50"/>
      <el-table-column prop="blogCollection" label="收藏数" width="50"/>
      <el-table-column prop="blogComment" label="评论数" width="50"/>
      <el-table-column prop="blogSource" label="文章来源"/>
      <el-table-column prop="createdTime" label="创建时间"/>
      <el-table-column prop="updateTime" label="修改时间"/>
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
                           @click="handleEdit(scope.row.typeId)">编辑
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini" type="danger" plain
                  @click="handleDelete(scope.row.typeId)">删除
                </el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <!--    添加弹窗-->
    <el-dialog
      title="添加"
      :visible.sync="addDialog">
      <blog-add :type="blog" @closeAddDialog="closeAddDialog" @getByPage="getByPage" />
    </el-dialog>

  </div>
</template>

<script>
  import blogApi from '@/api/blog'
  import BlogAdd from './blog-add'

  export default {
    name: "blog-list",
    components: {
      BlogAdd
    },
    data() {
      return {
        page: {
          currentPage: 1,
          pageSize: 20,
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
        addDialog: false, // 控制添加弹窗显示
      }
    },
    created() {
      this.getByPage(this.page);
    },
    methods: {
      getByPage(page) {
        blogApi.getByPage(page).then(res => {
          console.log(res)
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
    }
  }
</script>

