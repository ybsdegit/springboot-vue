package com.ybs.blog.controller;

import com.ybs.blog.pojo.Type;
import com.ybs.blog.service.TypeService;
import com.ybs.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TypeController
 *
 * @author Paulson
 * @date 2020/3/26 15:36
 */

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 添加类型
     * @param type
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Type type){
        typeService.save(type);
        return new Result<>("添加成功");
    }

    /**
     * 后台查询所有
     * @return
     */
    @GetMapping("/list")
    public Result<List<Type>> list(){
        List<Type> typeList = typeService.getAll();
        return new Result<>(typeList);
    }

    /**
     * 前台查询所有
     * @return
     */
    @GetMapping("/getList")
    public Result<List<Type>> getList(){
        List<Type> typeList = typeService.getTypeList();
        return new Result<>(typeList);
    }

    /**
     * 根据id更新
     * @param type
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody Type type){
        typeService.update(type);
        return new Result<>("更新成功");
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @PutMapping("/enable/{id}")
    public  Result<Object> enable(@PathVariable Integer id){
        typeService.enableById(id);
        return new Result<>("启用成功");
    }



    }
