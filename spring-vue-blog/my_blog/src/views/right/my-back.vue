<template>
  <div>
    <a-card hoverable class="my-back-card">
      <img src="@/assets/my-back.jpg" class="my-back">
      <img src="@/assets/header.gif" class="my-header">
      <div class="my-bottom-container">
        <div class="my-nickname">Paulson</div>
        <div class="my-signature">人生何其短，愿你我尽其欢，亦欢其尽</div>
        <a-divider/>
        <div class="social-container">
          <div class="social-text">社交</div>
          <div class="social-item">
            <a href="https://blog.csdn.net/qq_38534107">
              <img src="@/assets/icons/csdn.png" class="social-icon">
            </a>
            <a href="https://gitee.com/paulsonwier">
              <img src="@/assets/icons/gitee.png" class="social-icon">
            </a>
            <a href="https://blog.csdn.net/qq_38534107">
              <img src="@/assets/icons/qq.png" class="social-icon">
            </a>
            <a href="https://github.c1212om/ybsdegit">
              <img src="@/assets/icons/github.png" class="social-icon">
            </a>
          </div>
        </div>
        <a-divider/>
        <div class="social-container">
          <div class="social-text">加入我们:</div>
          <div class="join-item">
            <a href="">
              <img src="@/assets/icons/QQqun.png" class="social-icon">
            </a>
          </div>
        </div>
      </div>
    </a-card>
  </div>
</template>

<script>
  export default {
    name: "my-back"
  }
</script>

<style>
  .my-back {
    width: 100%;
    position: absolute;
    z-index: 0;
  }
  .my-back-card .ant-card-body {
    padding: 0 !important;
  }

  .my-header {
    position: relative;
    display: block;
    width: 100px;
    height: 100px;
    border: 5px solid #e5e5e5;
    border-radius: 50%;
    z-index: 1;
    margin: 75px auto 10px;
  }

  .my-bottom-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 280px;
    margin: auto auto 30px;
  }

  .my-nickname {
    margin: auto;
    font-size: 25px;
    font-weight: bold;
    line-height: 60px;
  }

  .my-signature {
    margin: auto;
    font-size: 15px;
    color: #878d99;
  }

  .social-container {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
  }

  .social-text {
    font-size: 15px;
    color: #878d99;
  }

  .social-icon {
    width: 40px;
    height: 40px;
  }

  .social-item {
    width: 200px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }

  .join-item {
    width: 200px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    /*margin: auto;*/
  }
</style>
