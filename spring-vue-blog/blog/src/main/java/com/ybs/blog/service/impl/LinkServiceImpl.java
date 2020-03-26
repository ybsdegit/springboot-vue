package com.ybs.blog.service.impl;

import com.ybs.blog.mapper.LinkMapper;
import com.ybs.blog.pojo.Link;
import com.ybs.blog.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public void save(Link link) {
        linkMapper.save(link);
    }

    @Override
    public void update(Link link) {
        linkMapper.update(link);
    }

    @Override
    public Link getById(Integer id) {
        return linkMapper.getById(id);
    }

    @Override
    public List<Link> getAll() {
        return linkMapper.getAll();
    }


    @Override
    public void deleteById(Integer id) {
        linkMapper.deleteById(id);
    }
}
