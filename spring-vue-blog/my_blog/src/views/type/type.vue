<template>
  <div>
    <div class="type-container">
      <div class="type-title">分类</div>
      <div class="type-count">共计 10 个分类</div>
      <div class="type-content">
        <div class="tyle-itme">Java</div>
        <div class="tyle-itme">Java</div>
        <div class="tyle-itme">Java</div>
        <div class="tyle-itme">Java</div>
        <div class="tyle-itme">Java</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Type'
}
</script>

<style scoped>

.type-container {
  background: #fff;
  padding: 40px 25px;
}
.type-title {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 60px;
}

.type-count {
  text-align: center;
  font-size: 20px;
  margin-bottom: 40px;
}

.type-content{
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
