<template>
  <div>
    <div class="blog-bg">
      <div class="blog-title">博客标题</div>
      <div class="blog-item">
        <div class="blog-time">发布时间: {{ about.createdTime }}</div>
        <div class="blog-meta">
          <a-icon type="eye" /> {{ about.aboutRead }} 阅读
          <a-icon type="heart" /> 0 收藏
          <a-icon type="like" /> 0 点赞
        </div>
      </div>
      <div class="blog-content">
        <!-- 内容开始 -->
        <div class="blog-content" v-html="about.aboutContent" />
      <!-- 内容结束 -->
      </div>
      <div class="blog-action">
        <div class="blog-good">
          <a-icon type="like" /> 点赞
        </div>
        <div class="blog-collection">
          <a-icon type="heart" /> 收藏
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import aboutApi from '@/api/about'
export default {
  name: 'About',
  data() {
    return {
      about: {} // 关于我的
    }
  },
  created() {
    this.getAbout()
  },
  methods: {
    getAbout() {
      aboutApi.read().then(res => {
        this.about = res.data
      })
    }
  }
}

</script>

<style scoped>
.header-img {
  width: 100px;
  height: 100px;
  border: 5px solid #e5e5e5;
  border-radius: 50%;
}
.blog-bg {
  background: #fff;
  padding: 40px 25px;
}
.blog-content {
  padding: 40px 25px;
}

.blog-title {
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  line-height: 70px;
}

.blog-item {
  padding: 0px 25px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 10px;
  margin-top: 10px;
}
.blog-action {
  display: flex;
  flex-direction: row;
  width: 300px;
  margin: auto;
  justify-content: space-evenly;
  font-size: 24px;
}

</style>
