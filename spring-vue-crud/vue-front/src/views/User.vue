<template>
    <div>
        <table>
            <tr>
                <td>编号</td>
                <td>姓名</td>
                <td>密码</td>
            </tr>
            <tr v-for="item in users">
                <td>{{item.id}}</td>
                <td>{{item.username}}</td>
                <td>{{item.password}}</td>
            </tr>
        </table>
        {{msg}}
    </div>
</template>

<script>
    export default {
        name: "User",
        data(){
            return {
                msg: 'Hello Vue',
                users:[
                    {
                        id: 1,
                        username: 'ybs',
                        password: 'mima'
                    }

                ]
            }
        },
        created() {
            // 初始化
            // alert('123')
            const _this = this
            axios.get("http://localhost:8081/user/findAll").then(function (resp) {
                console.log(resp)
                _this.users = resp.data;
            })
        }

    }
</script>

<style scoped>

</style>