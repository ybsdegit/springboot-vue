import request from '@/utils/request'
var group_name = 'log'
export default {

  getByPage(page) { // 分页查询
    return request({
      url: `/${group_name}/getByPage`,
      method: 'post',
      data: page
    })
  },

  deleteByIds(logIds) { // 批量删除
    return request({
      url: `/${group_name}/deleteByIds`,
      method: 'put',
      data: logIds
    })
  },
  deleteById(id) { // 根据id删除
    return request({
      url: `/${group_name}/delete/${id}`,
      method: 'delete',
      data: { id: id }
    })
  },

}
