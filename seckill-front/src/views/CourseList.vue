<template>
    <div id="course-list">
        <h1>所有课程</h1>
        <el-row class="list-filter">
            <!-- tab 切换 -->
            <el-col>
                <!-- 通过class绑定实现切换 -->
                <el-button :class="courseType == 0 ? 'el-button--primary' : 'el-button--text'"
                           size="mini" round @click="changeCourseType(0)">全部课程
                </el-button>
                <el-button :class="courseType == 1 ? 'el-button--primary' : 'el-button--text'"
                           size="mini" round @click="changeCourseType(1)">自然科学
                </el-button>
                <el-button :class="courseType == 2 ? 'el-button--primary' : 'el-button--text'"
                           size="mini" round @click="changeCourseType(2)">园艺栽培
                </el-button>
                <el-button :class="courseType == 3 ? 'el-button--primary' : 'el-button--text'"
                           size="mini" round @click="changeCourseType(3)">创意绘画
                </el-button>
                <el-button :class="courseType == 4 ? 'el-button--primary' : 'el-button--text'"
                           size="mini" round @click="changeCourseType(4)">摄影技巧
                </el-button>
            </el-col>
        </el-row>
        <el-row :gutter="30">
            <!-- 单个的卡片列 -->
            <el-col :span="6" v-for="(item, index) in this.courseList"
                    :index="index"
                    :key="index"
                    :class="courseType == 0 || courseType == item.courseType ? '' : 'hide'">
                <router-link :to="'/home/course/' + item.courseNo">
                    <div class="course">
                        <!-- 使用require方法请求图片地址实现图片绑定 -->
                        <div :style="{background: 'transparent url(' + require('../assets/images/course/' + item.coursePic) + ') no-repeat scroll center center'}"
                             class="course-img">
                            <!-- time div -->
                            <div class="course-date">
                                <!-- 使用过滤器实现日期格式化 -->
                                <time class="time">{{item.startTime | date-format}}</time>
                            </div>
                        </div>
                        <div class="course-info">
                            <div class="course-name"> 课程名: {{item.courseName}}</div>
                            <div class="teacher-name"> 老师名: {{item.teacherName}}</div>
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
        name: 'course-list',
        data() {
            return {
                courseList: [],
                courseType: 0
            }
        },
        created: function() {
            const _this = this

            this.axios.get('/api/courseList')
                .then(function (response) {
                    if (response.data.code == "200") {
                        _this.courseList = response.data.data;
                        console.log(response)
                        console.log(_this.courseList)
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
    @import '../assets/css/course-list.css';
</style>