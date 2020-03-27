import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
// eslint-disable-next-line no-unused-vars
import md5 from 'js-md5'

const state = {
    typeList: [] // 分类列表
}


const mutations = {
  SET_TYPE: (state, typeList) => {
    state.typeList = typeList
  }
}


export default {
  namespaced: true,
  state,
  mutations,
}

