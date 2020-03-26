package com.ybs.blog.service.impl;

import com.ybs.blog.mapper.BlogMapper;
import com.ybs.blog.mapper.TypeMapper;
import com.ybs.blog.pojo.Blog;
import com.ybs.blog.pojo.Type;
import com.ybs.blog.service.BlogService;
import com.ybs.blog.utils.IdWorker;
import com.ybs.blog.utils.Page;
import com.ybs.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客表服务实现类
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private IdWorker idWorker;

    @Override
    public void save(Blog blog) {
        blog.setBlogId(idWorker.nextId()+"");
        blogMapper.save(blog);
    }

    @Override
    public Blog getById(String id) {

        return blogMapper.getById(id);
    }

    @Override
    public void update(Blog blog) {
        blogMapper.update(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo readById(String id) {
        Blog blog = blogMapper.getById(id);
        // 阅读需要更新阅读数目
        blog.setBlogRead(blog.getBlogRead()+1);
        blogMapper.update(blog);

        // 转 VO
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        // 查询分类
        Type type = typeMapper.getById(blog.getBlogType());
        blogVo.setTypeName(type.getTypeName());

        return blogVo;
    }

    @Override
    public void deleteById(String id) {
        blogMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<BlogVo> getByPage(Page<BlogVo> page) {
        // 查询数据
        List<BlogVo> blogVoList = blogMapper.getByPage(page);
        page.setList(blogVoList);

        // 查询总数
        int totalCount = blogMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
