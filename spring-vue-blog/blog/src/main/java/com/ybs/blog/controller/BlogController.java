package com.ybs.blog.controller;

import com.ybs.blog.enums.ResultEnum;
import com.ybs.blog.pojo.Blog;
import com.ybs.blog.service.BlogService;
import com.ybs.blog.utils.Page;
import com.ybs.blog.utils.Result;
import com.ybs.blog.utils.StringUtils;
import com.ybs.blog.vo.BlogVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * BlogController
 *
 * @author Paulson
 * @date 2020/3/26 18:27
 */

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 保存
     * @param blog
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Blog blog){
        blogService.save(blog);
        return new Result<>("添加成功");
    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Blog> get(@PathVariable String id){
        Blog blog = blogService.getById(id);
        return new Result<>(blog);
    }

    /**
     * 更新
     * @param blog
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody Blog blog){
        blogService.update(blog);
        return new Result<>("更新成功");
    }

    /**
     * 根据Id阅读
     * 阅读数
     * @param id
     * @return
     */
    @GetMapping("/read/{id}")
    public Result<BlogVo> read(@PathVariable String id){
        BlogVo blogvo = blogService.readById(id);
        return new Result<>(blogvo);
    }

    /**
     * 根基id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Blog> delete(@PathVariable String id){
        blogService.deleteById(id);
        return new Result<>("删除成功");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<BlogVo>> getByPage(@RequestBody Page<BlogVo> page){

        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)){
            // 排序列不为空
            String[] sortColumns = {"blog_goods", "blog_read", "blog_collection",
                    "type_name", "blog_comment", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序参数不合法");
            }

        }
        page = blogService.getByPage(page);
        return new Result<>(page);
    }

    @GetMapping("/list")
    public Result<List<Blog>> getAll(){
        return new Result<>();
    }
}
