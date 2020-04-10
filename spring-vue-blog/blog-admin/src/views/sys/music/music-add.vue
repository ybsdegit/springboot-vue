<template>
  <div>
    <el-form ref="form" :model="music" label-width="80px" size="mini">
      <el-form-item label="名称">
        <el-input v-model="music.name"></el-input>
      </el-form-item>
      <el-form-item label="歌手">
        <el-input v-model="music.artist"></el-input>
      </el-form-item>
      <el-form-item label="封面">
        <el-upload
                  class="avatar-uploader"
                  :action="uploadUrl"
                  :headers="headers"
                  :on-success="uploadSuccess"
                  :show-file-list="false">
                  <img v-if="imageUrl" :src="imageUrl" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
      </el-form-item>

      <el-form-item label="歌曲文件">
        <el-upload
          :action="uploadUrl"
          :headers="headers"
          :limit="1"
          :on-success="uploadMusicSuccess"
          :file-list="fileList">
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="歌词">
        <el-input v-model="music.lrc"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="cancelForm">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :loading="loading">{{ loading ? '提交中 ...' : '确 定' }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import musicApi from '@/api/music'
  import { getToken } from '@/utils/auth'


  export default {
    name: 'music-add',
    data() {
      return {
        music: {
        },
        headers: { // 上传文件请求头
          Authorization: getToken(),
        },
        imageUrl: null, // 上传图片回显
        uploadUrl: process.env.VUE_APP_UPLOAD_API, // 上传图片路径
        typeList: this.$store.getters.typeList,
        loading: false,
        fileList: []
      }
    },
    methods: {
      onSubmit() {
        this.loading = true
        musicApi.save(this.music).then(res => {
          this.music = {}
          this.loading = false
          this.$message.success(res.msg)
          this.$emit('closeAddDialog')
          this.$emit('getByPage')
        })
          .catch(res => {
            this.loading = false
          })
      },
      cancelForm() {
        this.$emit('closeAddDialog')
      },
      uploadSuccess(res, file) {
        this.$message.success(res.msg)
        this.imageUrl = res.data
        this.music.cover = res.data
      },

      uploadMusicSuccess(res, file) {
        this.$message.success(res.msg)
        this.music.url = res.data
      },
    }
  }
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
