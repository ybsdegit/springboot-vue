<template>
  <div>
    <el-button type="primary" @click="openAddDra" class="add-button" size="mini">添加</el-button>

    <!-- 列表  -->
    <el-table :data="typeList" border style="width: 100%">
      <el-table-column prop="typeId" label="编号"/>
      <el-table-column prop="typeName" label="分类"/>
      <el-table-column prop="typeBlogCount" label="博客数"/>
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
              <i class="el-icon-arrow-down el-icon--right"></i></el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button size="mini" type="primary" plain
                  @click="handleEdit(scope.row.typeId)">编辑
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button v-if="scope.row.enable === 0"
                  size="mini" type="success" plain
                  @click="toEnable(scope.row.typeId)">启用
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button v-if="scope.row.enable === 1"
                  size="mini" type="warning" plain
                  @click="toDisEnable(scope.row.typeId)">弃用
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

    <!-- 添加抽屉   -->
    <el-drawer
      title="添加"
      :visible.sync="drawer">
      <type-add @getTypeList="getTypeList" @closeAddDra="closeAddDra"/>
    </el-drawer>

    <!--    修改弹窗-->
    <el-dialog
      title="修改"
      :visible.sync="updateDialog">
      <type-update :type="type" @closeUpdateDialog="closeUpdateDialog" @getTypeList="getTypeList"/>
    </el-dialog>
  </div>
</template>

<script>
  import typeApi from '@/api/type.js'
  import TypeAdd from "./type-add"
  import TypeUpdate from "./type-update"

  export default {
    components: {
      TypeAdd,
      TypeUpdate
    },
    name: 'type-list',
    data() {
      return {
        type: {},
        drawer: false, // 控制抽屉
        updateDialog: false,  // 控制修改弹窗
        typeList: []  // 类型数组
      }
    },
    created() {
      this.getTypeList()
    },
    methods: {
      getTypeList() {
        typeApi.listBack().then(res => {
          console.log(res)
          this.typeList = res.data
        })
      },
      handleEdit(id) {
        // 修改
        typeApi.get(id).then(res => {
          this.type = res.data
          this.updateDialog = true
        })

      },
      handleDelete(id) {
        this.$confirm('是否删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          typeApi.deleteById(id).then(res => {
            this.$message.success(res.msg)
            this.getTypeList()
          })
        });
      },
      toEnable(id){
        typeApi.enableById(id).then(res =>{
          this.$message.success(res.msg)
          this.getTypeList()
        })
      },
      toDisEnable(id){
        this.$confirm('是否弃用?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          typeApi.disableById(id).then(res =>{
            this.$message.success(res.msg)
            this.getTypeList()
          })
        })
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

