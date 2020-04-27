package com.ybs.blog.interceptor;

import com.ybs.blog.enums.ResultEnum;
import com.ybs.blog.exception.BlogException;
import com.ybs.blog.utils.ShiroUtils;
import com.ybs.blog.utils.StringUtils;
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

    /**
     * 放行的白名单
     */
    private static String[] whiteList = {
            "/about/read",
            "/blog/read",
            "/admin/login",
            "/user/login",
            "/user/register",
            "/link/list",
            "/music/getByPage",
            "/music/getList",
            "/type/getList",
            "/blog/recomRead",
            "/blog/getTimeLine",
            "/blog/getByPage",
            "/comment/getByBlog",
            "/admin/getAdmin"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (containsWhiteList(request.getRequestURI())) {
            return true;
        }
        // 获取token
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)) {
            // token不为空，获取当前登录用户
            Object loginUser = ShiroUtils.getLoginUser();
            if (loginUser != null) {
                // 说明token有效
                return true;
            }
        }
        throw new BlogException(ResultEnum.NOT_LOGIN);
    }

    /**
     * 判断url是否在白名单内
     *
     * @param s
     * @return
     */
    private boolean containsWhiteList(String s) {
        for (String url : whiteList) {
            if (s.contains(url)) {
                return true;
            }
        }
        return false;
    }
}
