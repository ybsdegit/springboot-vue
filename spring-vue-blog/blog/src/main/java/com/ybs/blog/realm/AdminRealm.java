package com.ybs.blog.realm;

import com.ybs.blog.enums.ResultEnum;
import com.ybs.blog.exception.BlogException;
import com.ybs.blog.pojo.Admin;
import com.ybs.blog.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AdminRealm
 * 处理管理员的能力和授权逻辑
 * @author Paulson
 * @date 2020/3/26 2:46
 */

public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        Admin admin = adminService.getByUsername(username);
        if (admin == null){
            throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在!");
        }
        return new SimpleAuthenticationInfo(admin, admin.getPassword(), this.getName());
    }
}
