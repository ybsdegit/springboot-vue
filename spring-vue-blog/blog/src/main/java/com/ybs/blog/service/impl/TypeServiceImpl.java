package com.ybs.blog.service.impl;

import com.ybs.blog.enums.StateEnums;
import com.ybs.blog.exception.BlogException;
import com.ybs.blog.mapper.TypeMapper;
import com.ybs.blog.pojo.Type;
import com.ybs.blog.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务实现类
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void save(Type type) {
        // 查询分类是否存在
        Type t = typeMapper.getByName(type.getTypeName());
        if (t != null){
            throw new BlogException("该分类已存在");
        }
        typeMapper.insert(type);
    }

    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }

    @Override
    public List<Type> getTypeList() {
        return typeMapper.getTypeList();
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public void enableById(Integer id) {
        // 先查再启用
        Type type = typeMapper.getById(id);
        type.setEnable(StateEnums.ENABLE.getCode());
        typeMapper.update(type);
    }

}
