<template>
  <div>
    <el-button type="primary" @click="openAddDra" class="add-button" size="mini">添加</el-button>

    <!-- 列表  -->
    <el-table :data="linkList" border style="width: 100%">
      <el-table-column prop="linkId" label="编号"/>
      <el-table-column prop="linkName" label="名称"/>
      <el-table-column prop="linkUrl" label="链接地址"/>
      <el-table-column prop="createdTime" label="创建时间" sortable="custom"/>
      <el-table-column prop="updateTime" label="修改时间" sortable="custom"/>
      <el-table-column label="操作">
        <template slot-scope="scope">

          <el-dropdown>
            <el-button type="primary" size="mini">操作
              <i class="el-icon-arrow-down el-icon--right"></i></el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                  @click="handleEdit(scope.row.linkId)">编辑
                </el-button>
              </el-dropdown-item>

              <el-dropdown-item>
                <el-button
                  size="mini" type="danger" plain
                  @click="handleDelete(scope.row.linkId)">删除
                </el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加抽屉   -->
    <el-drawer
      title="添加"
      :visible.sync="drawer">
      <link-add @getLinkList="getLinkList" @closeAddDra="closeAddDra"/>
    </el-drawer>

    <!--    修改弹窗-->
    <el-dialog
      title="修改"
      :visible.sync="updateDialog">
      <lind-update :link="link" @closeUpdateDialog="closeUpdateDialog" @getLinkList="getLinkList"/>
    </el-dialog>
  </div>
</template>

<script>
  import linkApi from '@/api/link.js'
  import LindUpdate from "./link-update"
  import LinkAdd from "./link-add"

  export default {
    components: {
      LinkAdd,
      LindUpdate
    },
    name: 'link-list',
    data() {
      return {
        link: {},
        drawer: false, // 控制抽屉
        updateDialog: false,  // 控制修改弹窗
        linkList: []  // 类型数组
      }
    },
    created() {
      this.getLinkList()
    },
    methods: {
      getLinkList() {
        linkApi.listBack().then(res => {
          console.log(res)
          this.linkList = res.data
        })
      },
      handleEdit(id) {
        // 修改
        linkApi.get(id).then(res => {
          this.link = res.data
          this.updateDialog = true
        })

      },
      handleDelete(id) {
        this.$confirm('是否删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          linkApi.deleteById(id).then(res => {
            this.$message.success(res.msg)
            this.getLinkList()
          })
        });
      },
      openAddDra() {
        // 打开添加弹窗
        this.drawer = true
      },
      closeAddDra() {
        this.drawer = false
      },
      closeUpdateDialog() {
        this.updateDialog = false
      }
    },
  }
</script>

