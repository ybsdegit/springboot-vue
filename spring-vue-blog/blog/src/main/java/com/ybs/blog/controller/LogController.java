package com.ybs.blog.controller;

import com.ybs.blog.enums.ResultEnum;
import com.ybs.blog.pojo.Log;
import com.ybs.blog.service.LogService;
import com.ybs.blog.utils.Page;
import com.ybs.blog.utils.Result;
import com.ybs.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.Pipe;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

/**
 * LogController
 *
 * @author Paulson
 * @date 2020/3/26 22:51
 */

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/getByPage")
    public Result<Page<Log>> getByPage(@RequestBody Page<Log> page){
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)){
            // 排序列不为空
            String[] sortColumns = {"log_url","log_status", "log_method","log_time", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序参数不合法");
            }
        }
        page = logService.getByPage(page);

        return new Result<>(page);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable Integer id){
        logService.deleteById(id);
        return new Result<>("删除成功");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PutMapping("/deleteByIds")
    public Result<Object> deleteByIds(@RequestBody List<Integer> ids){
        logService.deleteByIds(ids);
        return new Result<>("删除成功");    }

    @GetMapping()
    public Result<Object> test(){
        return new Result<>();
    }
}
