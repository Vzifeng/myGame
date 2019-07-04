package com.example.game.utils;

import com.example.game.po.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;


/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 11:14 2019/7/2/0002
 * @Version ： $version$
 */
public class SessionUtil {
    private static final String keyPrefix = "shiro_redis_session:";



    public static Integer getUserId(){
        Session session = SecurityUtils.getSubject().getSession();
        Object userId = session.getAttribute("userId");
        return (Integer) userId;
    }

    public static User getUser(){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("userSession");
        return user;
    }
}
