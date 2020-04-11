<template>
  <div>
    <a-card title="推荐阅读" :headStyle="headStyle" :bodyStyle="bodyStyle">
      <router-link to="/" class="recom-card">
        <div class="blog-title">文章标题</div>
        <div class="blog-type">分类:
          <a-tag color="blue">前端</a-tag>
        </div>
        <div class="blog-bottom">
          <div class="blog-time">2020-04-11 22:00:00</div>
          <div class="blog-meta">
            <a-icon type="eye"/> 0
            <a-icon type="heart"/> 0
            <a-icon type="like"/> 0
            <a-icon type="message"/> 0
          </div>
        </div>
      </router-link>
    </a-card>
  </div>
</template>

<script>
  export default {
    name: "recommend",
    data() {
      return {
        headStyle: {
          fontSize: '18px',
          fontWeight: 'bold',
          lineHeight: '15px',
          borderLeft: '5px solid #409eff'
        },
        bodyStyle: {
          maxHeight: '530px',
          overflow: 'hidden'
        }
      }
    },
    methods: {
      test() {

      },
    }
  }

</script>

<style>
  .ant-card-bordered {
    border: none !important;
  }

  .recom-card {
    display: flex;
    flex-direction: column;
    color: #3e4149;
  }

  .recom-card:hover {
    color: #3e4149;
  }

  .blog-title {
    font-size: 16px;
    line-height: 40px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .blog-type {
    line-height: 40px;
  }

  .blog-bottom {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    font-size: 12px;
    color: #b8b8b8;
    margin-bottom: 2px;
  }


</style>
