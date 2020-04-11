import request from '@/utils/request'
var group_name = 'link'
export default {

  save(link) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: link
    })
  },
  listBack() { // 查询
    return request({
      url: `/${group_name}/list`,
      method: 'get'
    })
  },
  update(link) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: link
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
