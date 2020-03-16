<template>
    <div id="course" v-loading.fullscreen.lock="load"
         element-loading-text="秒杀排队中"
         element-loading-background="rgba(0, 0, 0, 0.8)">
        <!-- 面包屑导航 -->
        <div>
            <el-breadcrumb separator="/">
                <el-breadcrumb-item to="/home/course/list">首页</el-breadcrumb-item>
                <el-breadcrumb-item to="/home/course/list">课程列表</el-breadcrumb-item>
                <el-breadcrumb-item>{{course.courseName}}</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <h1>{{course.courseName}}</h1>
        <!-- 整个第三行的外层div - 阴影 -->
        <div class="detail">
            <el-row>
                <el-col :span="16" class="detail-left">
                    <!-- 左侧底层图片div -->
                    <div class="detail-pic"
                         :style="{background: 'transparent url(' + require('../assets/images/course/' + course.coursePic) + ') no-repeat scroll center center'}">

                        <!-- 上层蓝色前景色div -->
                        <div class="blue-bg">
                            <!-- 内容布局div -->
                            <div class="detail-content">
                                <!-- 课程名加粗div -->
                                <span class="detail-content-text">{{course.courseName}}</span>
                                <!-- 倒计时div -->
                                <span class="detail-content-timer">{{courseTimerStatus}}</span>
                            </div>
                            <!-- 播放图标div -->
                            <img class="course-play" src="../assets/images/play.png"/>
                        </div>
                    </div>
                </el-col>
                <el-col :span="8">
                    <!-- 右侧课程信息div -->
                    <div class="detail-right">
                        <!-- 右上讲师信息div -->
                        <div class="detail-teacher-container">
                            <!-- 讲师头像div -->
                            <img src="../assets/images/sam.png"></img>
                            <!-- 讲师姓名 -->
                            <span>讲师: {{course.teacherName}}</span>
                        </div>
                        <!-- 右下课程信息div-->
                        <div class="detail-info">
                            <!-- 报名按钮 -->
                            <div>
                                <!--  0:立即报名, 1:查看订单, 2:课程售罄, 3:已结束  -->
                                <el-button id="book-btn" type="info" v-if="showButtonType == 0"
                                           ref="book" :class="isBookButtonDisabled ? '' : 'el-button--primary'"
                                           :disabled="isBookButtonDisabled"
                                           @click="book">
                                    <span>立即报名</span>
                                </el-button>
                                <router-link to="/home/order/list">
                                    <el-button type="info" v-if="showButtonType == 1" class="el-button--primary"
                                               id="trun-btn">
                                        <span>查看订单</span>
                                    </el-button>
                                </router-link>
                                <el-button type="info" disabled v-if="showButtonType == 2">
                                    <span>课程售罄</span>
                                </el-button>
                                <el-button type="info" disabled v-if="showButtonType == 3">
                                    <span>已结束</span>
                                </el-button>
                            </div>
                            <!-- 课程时间、描述div -->
                            <div class="">
                                <br/>
                                <p>课程编号: {{course.courseNo}}<br/></p>
                                <p>课程开始时间: {{course.startTime | date-format}}<br/></p>
                                <p>课程结束时间: {{course.endTime | date-format}}<br/><br/></p>
                                <p>课程描述: &nbsp; {{course.courseDesciption}}</p>
                            </div>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'course',
        data() {
            return {
                course: {
                    "courseNo": "",
                    "courseName": "",
                    "teacherName": "",
                    "courseDesciption": "",
                    "coursePeriod": "",
                    "startTime": 0,
                    "endTime": 0,
                    "courcePrice": 0,
                    "stockQuantity": 0,
                    "courseType": 0,
                    "coursePic": "c1.png"
                },
                courseStatus: '',  //  0还没开始  1正在进行中 2已结束
                remainTime: '11111',
                courseTimerStatus: '距离开课时间还有 1天20小时20分钟30秒',
                timer: null,
                isTimerStop: true,
                showButtonType: 0,
                isBookButtonDisabled: false,
                load: false
            }
        },
        created() {
            //距离开课时间还有 1天20小时20分钟30秒
            const self = this;
            self.axios.get('/api/courseDetail/' + self.$route.params.courseNo)
                .then(function (response) {
                    self.course = response.data.data.course;
                    console.log(response)
                    self.courseStatus = response.data.data.courseStatus
                    self.remainTime = response.data.data.remainTime
                    self.checkTimerStatus(self.courseStatus);
                })
                .catch(function (error) {
                    self.$message.error("系统错误")
                });
        },
        methods: {
            book() {
               /* var self = this;
                self.axios.get('/api/getPath/' + self.$route.params.courseNo)
                    .then(function (response) {
                        var path = response.data;
                        var courseNo = self.$route.params.courseNo;
                        self.axios.get(`/api/${path}/seckill/${courseNo}`)
                            .then(function (response) {
                                self.getResult(response)
                            })
                            .catch(function (error) {
                                self.$message.error('出错')
                            });
                    })
                    .catch(function (error) {
                        self.$message.error('出错')
                    });*/
            },
            getResult(response) {
                var code = response.data.code;
                var self = this;
                switch (code) {
                    case 200:
                        // self.$message.success(response.data.message)
                        self.showButtonType = 1;
                        self.load = false;
                        break;
                    case 500100:
                        var self = this;
                        self.isBookButtonDisabled = true;
                        self.load = true;
                        self.axios.get('/api/seckillResult/' + self.$route.params.courseNo)
                            .then(function (response) {
                                self.getResult(response);
                            })
                            .catch(function (error) {
                                self.$message.error("系统错误")
                            });
                        break;
                    case 500101:
                        self.load = false;
                        self.isBookButtonDisabled = false;
                        self.$message.error(response.data.message);
                        break;
                    case 500102:
                        self.load = false;
                        self.showButtonType = 1;
                        self.$message.error(response.data.message);
                        break;
                    case 500104:
                        self.load = false;
                        self.isBookButtonDisabled = false;
                        self.$message.error(response.data.message);
                        break;
                    case 500105:
                        self.load = false;
                        self.isBookButtonDisabled = false;
                        self.$message.error(response.data.message);
                        break;
                    default:
                        self.$message.error('系统错误')
                        self.load = false;
                        break;
                }
            },
            checkTimerStatus(courseStatus) {
                var self = this;

                //courseStatus: '',  //  0还没开始  1正在进行中 2已结束
                if (courseStatus == 0){
                    self.isTimerStop = false;
                    // self.courseTimerStatus = '距离开课时间还有 1天20小时20分钟30秒';
                    self.showButtonType = 0;
                    self.isBookButtonDisabled = true;
                    self.startTimer()
                }else if(courseStatus == 1){
                    if (self.course.stockQuantity > 0) {
                        self.courseTimerStatus = '开始报名';
                        self.showButtonType = 0;
                        self.isBookButtonDisabled = false;
                    } else {
                        self.courseTimerStatus = '已售罄';
                        self.showButtonType = 2;
                    }
                }else {
                    self.courseTimerStatus = '已结束';
                    self.showButtonType = 3;
                }

            },
            startTimer() {
                var self = this;
                self.timer = setInterval(function () {
                    if (self.isTimerStop) {
                        clearInterval(self.timer)
                    }
                    self.updateTimer()
                }, 1000)
            },
            updateTimer() {
                var self = this;
                var startTime = new Date(self.course.startTime);
                var currentTime = new Date();
                var leftTime = parseInt((startTime.getTime() - currentTime.getTime()))
                let time = leftTime /1000;
                let days = Math.floor(time /60 /60 /24);
                let hours = Math.floor(time /60 /60) %24;
                let mins = Math.floor(time /60) %60;
                let sec = Math.floor(time) %60;
                self.courseTimerStatus = `距离开课时间还有 ${days}天${hours}小时${mins}分钟${sec}秒`
                if (leftTime <= 0) {
                    self.isTimerStop = true;
                }
            }
        }
    }
</script>

<style>
    @import '../assets/css/course.css'
</style>
