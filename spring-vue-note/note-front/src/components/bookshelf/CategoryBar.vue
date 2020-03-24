<template>
    <div>
        <el-tabs v-model="currentCid" type="card" @tab-click="handleClick">
            <el-tab-pane v-for="(item, i) in categories" :label="item.name" :key="i" :name="item.id.toString()"></el-tab-pane>
        </el-tabs>
        <book-shelf/>
    </div>
</template>

<script>
    import BookShelf from "./BookShelf";
    export default {
        name: "CategoryBar",
        components: {BookShelf},
        data() {
            return {
                categories: [],
                currentCid: '',
            }
        },
        mounted() {
            const _this = this
            this.axios.get('/categories')
                .then(function (response) {
                    console.log(response)
                    if (response.data.status == 200) {
                        _this.categories = response.data.data
                        _this.$message.success(response.data.msg);
                        const temp = _this.categories.length
                        if (temp>0){
                            _this.currentCid = _this.categories[temp-1].id.toString()
                            _this.$emit('categorySelect')
                        }
                    } else {
                        _this.$message.error(response.data.msg);
                    }
                })
                .catch(function (response) {
                    _this.$message.error(response.data);
                })
        },
        methods: {
            handleClick(tab, event) {
                this.$emit('categorySelect')
            },
        }

    }

</script>

<style scoped>

</style>