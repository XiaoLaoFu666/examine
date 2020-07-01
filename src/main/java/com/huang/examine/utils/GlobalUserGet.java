package com.huang.examine.utils;

import com.huang.examine.access.AccessInterceptor;
import com.huang.examine.entity.User;
import com.huang.examine.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/7 21:30
 */
@Service
public class GlobalUserGet {
    @Autowired
    UserService userService;

    @Autowired
    AccessInterceptor accessInterceptor;

    /**
     * 获取当前用户
     * */
    public User getCurrentUser(HttpServletRequest request,HttpServletResponse response){
        User user = accessInterceptor.getUser(request,response);
        return user;
    }

    private  User getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(UserService.COOKI_NAME_TOKEN);
        String cookieToken = getCookieValue(request, UserService.COOKI_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return userService.getStudentByToken(response, token);
    }

    private  String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[]  cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public User getTeacher(HttpServletRequest request, HttpServletResponse response) {
        User user = accessInterceptor.getTeacherUser(request,response);
        return user;
    }
}
