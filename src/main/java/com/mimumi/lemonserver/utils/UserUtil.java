package com.mimumi.lemonserver.utils;

import com.mimumi.lemonserver.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2017/2/17.
 */
public class UserUtil {

    /*
    * 获取当前用户信息
    * */
    public static User getCurrentUser() {
        User user=null;
        try {
            Subject subject = SecurityUtils.getSubject();

            if(subject.isAuthenticated()){
                user=(User)subject.getPrincipal();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return user;
    }

}
