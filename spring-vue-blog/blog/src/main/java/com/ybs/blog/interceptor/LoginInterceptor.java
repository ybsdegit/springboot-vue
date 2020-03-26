package com.ybs.blog.interceptor;

import com.ybs.blog.enums.ResultEnum;
import com.ybs.blog.exception.BlogException;
import com.ybs.blog.pojo.Admin;
import com.ybs.blog.utils.ShiroUtils;
import com.ybs.blog.utils.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginInterceptor
 *
 * @author Paulson
 * @date 2020/3/26 3:03
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取 token
        String token = request.getHeader("Authorization");
        try {
            if (StringUtils.isNotBlank(token)){
                Admin loginAdmin = (Admin) ShiroUtils.getLoginUser();
                if (loginAdmin != null){
                    // token 有效
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new BlogException();
        }

        throw new BlogException(ResultEnum.NOT_LOGIN);
    }
}
