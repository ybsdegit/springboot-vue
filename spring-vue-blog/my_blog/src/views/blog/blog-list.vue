<template>
  <div>
    <div class="button-container">
      <div class="button-text">{{ name }}</div>
      <div class="button-menu">
        <a-menu v-model="current" mode="horizontal">
          <a-menu-item key="1">
            最新
          </a-menu-item>
          <a-menu-item key="2">
            点赞最多
          </a-menu-item>
          <a-menu-item key="3">
            评论最多
          </a-menu-item>
          <a-menu-item key="4">
            收藏最多
          </a-menu-item>
        </a-menu>
      </div>
    </div>
    <div class="blog-list-container">
      <a-card :body-style="blogbodyStyle" class="blog-card">
        <div class="blog-main">
          <div class="blog-image">
            <img src="@/assets/springboot.jpg" class="blog-cover">
          </div>
          <router-link to="/info/1" class="blog-container">
            <div class="blog-title">哈哈哈</div>
            <div class="blog-comment">博客介绍介绍介绍介绍</div>
            <div class="blog-bottom">
              <div class="blog-type">
                分类: <a-tag color="blue">前端</a-tag>
              </div>
              <div class="blog-meta">
                <div class="blog-time">2020-04-22</div>
                <div class="blog-other">
                  <a-icon type="eye" /> 0
                  <a-icon type="heart" /> 0
                  <a-icon type="like" /> 0
                  <a-icon type="message" /> 0
                </div>
              </div>
            </div>
          </router-link>
        </div>
      </a-card>
    </div>

    <div class="blog-pagination">
      <a-pagination :show-total="total => `共 ${total} 条`" show-quick-jumper :default-current="2" :total="500" @change="pageChage" />
      <br>
    </div>

  </div>
</template>

<script>
export default {
  props: {
    name: {
      type: String,
      default: '博客'
    }
  },
  data() {
    return {
      current: ['1'],
      blogbodyStyle: {
        padding: '18px'
      }
    }
  },
  methods: {
    pageChage(pageNumber) {
      console.log('Page: ', pageNumber)
    }
  }
}
</script>

<style scoped>
.button-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 0 5px;
  align-items: center;
  line-height: 70px;
}

.button-text {
  font-size: 20px;
}

.ant-menu-horizontal {
  border-bottom: none !important;
}

.ant-menu {
  background: none !important;
}

.blog-container {
  width: 550px;
  min-height: 130;
}

a {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  color: #3e4149;
}

a:hover {
  color: #3e4149
}

.blog-title {
  font-size: 10px;
  margin-bottom: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-bottom: 10px;
}

.blog-comment {
  font-size: 14px;
  color: #9c9ea8;
  line-height: 40px;
  margin-bottom: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  max-height: 90px;
}

.blog-bottom {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.blog-meta {
  font-size: 12px;
  color: #9c9ea8;
}

.blog-image {
  width: 230px;
  margin-right: 20px;
}

.blog-cover {
  width: 100%;
  border-radius: 5px;
}

.blog-main {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.blog-card {
  margin-bottom: 15px;
  border-radius: 5px;
}
</style>
