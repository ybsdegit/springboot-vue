package com.ybs.blog.controller;

import com.ybs.blog.pojo.Link;
import com.ybs.blog.service.LinkService;
import com.ybs.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LinkController
 *
 * @author Paulson
 * @date 2020/3/26 17:11
 */

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 添加
     * @param link
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Link link){
        linkService.save(link);
        return new Result<>("添加成功");
    }

    /**
     * 更新
     * @param link
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody Link link){
        linkService.update(link);
        return new Result<>("修改成功");
    }

    /**
     * 根基id查询
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Link> get(@PathVariable Integer id){
        Link link = linkService.getById(id);
        return new Result<>(link);
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public Result<List<Link>> list(){
        List<Link> links = linkService.getAll();
        return new Result<>(links);
    }

    /**
     * 根据Id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable Integer id){
        linkService.deleteById(id);
        return new Result<>("删除成功");
    }


}
