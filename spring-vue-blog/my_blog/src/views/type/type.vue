<template>
  <div>
    <div class="type-container">
      <div class="type-title">分类</div>
      <div class="type-count">共计 10 个分类</div>
      <div class="type-content">
        <router-link v-for="item in typeList" :key="item.typeId" :to="'/blogList/'+item.typeId" class="type-item">{{ item.typeName }} （{{ item.typeBlogCount }}）</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import typeApi from '@/api/type'
export default {
  name: 'Type',
  data() {
    return {
      typeList: [] // 分类列表
    }
  },
  created() {
    this.getTypeList()
  },
  methods: {
    getTypeList() {
      typeApi.getList().then(res => {
        this.typeList = res.data
      })
    }
  }
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
