import request from '@/utils/request'
var group_name = 'type'
export default {

  save(type) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: type
    })
  },
  listBack() { // 后台查询
    return request({
      url: `/${group_name}/list`,
      method: 'get'
    })
  },
  getList() { // 前台查询
    return request({
      url: `/${group_name}/getList`,
      method: 'get'
    })
  },
  update(type) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: type
    })
  },
  enableById(id) { // 启用
    return request({
      url: `/${group_name}/enable/${id}`,
      method: 'put',
    })
  },
  disableById(id) { // 启用
    return request({
      url: `/${group_name}/disable/${id}`,
      method: 'put',
    })
  },
  deleteById(id) { // 根据id删除
    return request({
      url: `/${group_name}/delete/${id}`,
      method: 'delete',
      data: { id: id }
    })
  },
  get(id) { // 根据id查询
    return request({
      url: `/${group_name}/get/${id}`,
      method: 'get'
    })
  },
}
