package com.ybs.blog.mapper;

import com.ybs.blog.pojo.User;
import com.ybs.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserMapper
 *
 * @author Paulson
 * @date 2020/3/26 0:33
 */
@Component
public interface UserMapper {

    /**
     * 添加
     * @param user
     */
    void save(User user);

    /**
     * 更新
     * @param user
     */
    void update(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<User> getByPage(Page<User> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<User> page);

    /**
     * 根据id列表查询
     * @param userIds
     * @return
     */
    List<User> getByIds(List<Integer> userIds);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 修改个人信息
     * @param user
     */
    void updateInfo(User user);
}
