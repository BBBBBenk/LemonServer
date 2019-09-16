package com.mimumi.lemonserver.security;

import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.service.inter.IUserService;
import com.mimumi.lemonserver.utils.JWTUtil;
import com.mimumi.lemonserver.dto.JWTToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统安全认证实现类
 */
@Component
public class SystemShiroRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;


    public SystemShiroRealm() {
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)   {
        String token = (String) authcToken.getCredentials();
        // 解密获得手机号码，用于和数据库进行对比
        String mobile = JWTUtil.getMobile(token);
        if (mobile == null) {
            throw new AuthenticationException("无效的Token");
        }

         User user = userService.checkOpenidIsExists(mobile);
        if (user == null) {
            throw new AuthenticationException("手机号不存在,请重新输入");
        }

        if (! JWTUtil.verify(token, mobile, user.getOpenid())) {
            throw new AuthenticationException("Token失效或过期");
        }

        return new SimpleAuthenticationInfo(user, token,  getName());
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

       /* String username = JWTUtil.getUsername(principals.toString());
        User user=userService.getByUserName(username);

        if(user!=null){
            //角色
            List<Role> roleList = userService.getUserRole(user.getUserid());
            for (Role role : roleList){
                info.addRole(role.getRolecode());
            }
            //权限
            List<Permission> permissionList = userService.getUserPerm(user.getUserid());
            for (Permission perm : permissionList){
                if (StringUtils.isNotBlank(perm.getPermcode())){
                    info.addStringPermission(perm.getPermcode());
                }
            }
        }
*/
        return info;
    }
}