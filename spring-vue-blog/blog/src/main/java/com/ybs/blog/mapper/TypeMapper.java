package com.ybs.blog.mapper;

import com.ybs.blog.pojo.Type;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TypeMapper
 *
 * @author Paulson
 * @date 2020/3/26 0:33
 */
@Component
public interface TypeMapper {
    /**
     * 根据名称查询
     * @param typeName
     * @return
     */
    Type getByName(String typeName);

    /**
     * 插入
     * @param type
     */
    void insert(Type type);

    List<Type> getAll();

    List<Type> getTypeList();

    void update(Type type);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    Type getById(Integer id);

    /**
     * 根据ID删除
     * @param id
     */
    void deleteById(Integer id);
}
