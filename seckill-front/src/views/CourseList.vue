<template>
    <div id="course-list">
        <h1>所有课程</h1>
        <el-row class="list-filter">
            <!-- tab 切换 -->
            <el-col>
                <!-- 通过class绑定实现切换 -->
                <el-button class="el-button--primary"
                           size="mini" round @click="changeCourseType(0)">全部课程
                </el-button>
                <el-button class="el-button--text"
                           size="mini" round>自然科学
                </el-button>
                <el-button class="el-button--text"
                           size="mini" round>园艺栽培
                </el-button>
                <el-button class="el-button--text"
                           size="mini" round>创意绘画
                </el-button>
                <el-button class="el-button--text"
                           size="mini" round>摄影技巧
                </el-button>
            </el-col>
        </el-row>
        <el-row :gutter="30">
            <!-- 单个的卡片列 -->
            <el-col :span="6" v-for="(item, index) in 6"
                    :index="index"
                    :key="index"
                    class="">
                <!-- 0 == flag || item.courseType == flag ? '' : 'hide' -->
                <!-- card div -->
                <router-link to="/home/course/xxx">
                    <div class="course">
                        <!-- img div -->
                        <!-- 使用require方法请求图片地址实现图片绑定 -->
                        <div :style="{background: 'transparent url(' + require('../assets/images/course/c1.jpg') + ') no-repeat scroll center center'}"
                             class="courseimg">
                            <!-- time div -->
                            <div class="course-date">
                                <!-- 使用过滤器实现日期格式化 -->
                                <time class="time">2018-06-01 11:11</time>
                            </div>
                        </div>
                        <!-- info div -->
                        <div class="course-info">
                            <!-- class name div -->
                            <div class="course-name"> 课程名</div>
                            <!-- teacher name div -->
                            <div class="teacher-name"> 老师名</div>
                        </div>
                    </div>
                </router-link>
            </el-col>
        </el-row>
    </div>
</template>

<style scoped> @import '../assets/css/course-list.css'; </style>


<script>

    import Vue from "vue";

    export default {
        name: "CourseList",
        data () {
            return{
                courseList: [],
                courseType: 0
            }
        },
        created() {
            const _this = this
            this.axios.get('/api/courseList')
                .then(function (response) {
                    console.log(response)
                    if (response.data.code == "200") {
                        console.log(response)
                        // _this.$store.dispatch('changeMyUser', {username: _this.user.username})
                        _this.$message.success(response.data.message);
                    } else {
                        _this.$message.error(response.data.message);
                    }
                })
                .catch(function (error) {
                    _this.$message.error("系统错误")
                });
        },
        methods: {
            changeCourseType: function (type) {
                this.courseType = type
            }
        }

    }
</script>

<style scoped>

</style>