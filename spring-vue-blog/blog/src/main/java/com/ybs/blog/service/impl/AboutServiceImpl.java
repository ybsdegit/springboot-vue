package com.ybs.blog.service.impl;

import com.ybs.blog.enums.ResultEnum;
import com.ybs.blog.enums.StateEnums;
import com.ybs.blog.exception.BlogException;
import com.ybs.blog.mapper.AboutMapper;
import com.ybs.blog.pojo.About;
import com.ybs.blog.service.AboutService;
import com.ybs.blog.utils.Page;
import com.ybs.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.nimbus.State;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Service
@Slf4j
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutMapper aboutMapper;

    @Override
    public void save(About about) {
        aboutMapper.save(about);
    }

    @Override
    public void update(About about) {
        aboutMapper.updateById(about);
    }

    @Override
    public About getById(Integer id) {
        return aboutMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public About read() {
        About about = aboutMapper.getAbout();
        if (about == null){
            return null;
        }
        // 更新阅读数
        about.setAboutRead(about.getAboutRead() + 1);
        aboutMapper.updateById(about);
        return about;
    }

    @Override
    public void deleteById(Integer id) {
        aboutMapper.deleteById(id);
    }

    @Override
    public void enableById(Integer id) {
        // 查询是否存在已启动的
        About about = aboutMapper.getAbout();
        if (about != null){
            throw new BlogException(ResultEnum.ERROR.getCode(), "当前已存在已启用的关于我的");
        }
        about = aboutMapper.getById(id);
        about.setEnable(StateEnums.ENABLE.getCode());
        aboutMapper.updateEnable(about);
    }

    @Override
    public void disableById(Integer id) {
        About about = aboutMapper.getById(id);
        about.setEnable(StateEnums.NOT_ENABLE.getCode());
        aboutMapper.updateEnable(about);
    }

    @Override
    public Page<About> getByPage(Page<About> page) {
        // 查询数据
        List<About> aboutList = aboutMapper.getByPage(page);
        page.setList(aboutList);

        // 查询总数
        int totalCount = aboutMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
