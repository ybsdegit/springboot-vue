package com.ybs.blog.service;

import com.ybs.blog.pojo.Music;
import com.ybs.blog.utils.Page;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
public interface MusicService {
    /**
     * 保存
     * @param music
     */
    void save(Music music);

    /**
     * 更新
     * @param music
     */
    void update(Music music);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Music getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 弃用
     * @param id
     */
    void disableById(Integer id);

    /**
     * 分页
     * @param page
     * @return
     */
    Page<Music> getByPage(Page<Music> page);

    /**
     * 前台查询
     * @return
     */
    List<Music> getList();
}
