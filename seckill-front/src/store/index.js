import Vue from 'vue'
import Vuex from 'vuex'
import qs from 'qs'

import th from "element-ui/src/locale/lang/th";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        isLogin: false,
        user: {
            username: 'null'
        },

    },
    mutations: {
        login(state, payload) {
            state.isLogin = true
            state.user.username = payload.username
        },
        logout(state, payload) {
            state.isLogin = false
            state.user.username = payload.username
        }
    },
    actions: {
        /*changeMyUser(context, payload) {
            context.commit('changeUserName', payload)
        },*/
        login(context, payload) {
            return new Promise((resolve, reject) => {
                Vue.axios.post('/api/login', qs.stringify(payload))
                    .then(function (response) {
                        console.log(response)
                        if (response.data.code == "200") {
                            // _this.$store.dispatch('changeMyUser', {username: _this.user.username})
                            // _this.$message.success(response.data.message);
                            // _this.$router.push("/")
                            resolve(response)
                            context.commit('login', payload)
                        } else {
                            // _this.$message.error(response.data.message);
                            // return false;
                            reject(response)
                        }
                    })
                    .catch(function (error) {
                        // _this.$message.error("系统错误")
                        var errorData = {
                            message: '系统错误'
                        }
                        reject(errorData)
                    });
            })

        }
    },
    getters: {
        getUserName(state) {
            return state.user.username;
        }
    },
    modules: {}
})
