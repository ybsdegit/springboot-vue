package com.ybs.note.service;

import com.ybs.note.mapper.CategoryMapper;
import com.ybs.note.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService
 *
 * @author Paulson
 * @date 2020/3/24 1:01
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAll(){
        return categoryMapper.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Category getById(Integer id){
        return categoryMapper.findById(id).get();
    }


}
