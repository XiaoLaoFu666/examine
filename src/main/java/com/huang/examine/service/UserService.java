package com.huang.examine.service;

import com.huang.examine.dao.StudentDao;
import com.huang.examine.dao.TeacherDao;
import com.huang.examine.entity.LoginVo;
import com.huang.examine.entity.Student;
import com.huang.examine.entity.Teacher;
import com.huang.examine.entity.User;
import com.huang.examine.redis.RedisService;
import com.huang.examine.redis.StudentKey;
import com.huang.examine.redis.TeacherKey;
import com.huang.examine.result.CodeMsg;
import com.huang.examine.result.Result;
import com.huang.examine.utils.MD5Util;
import com.huang.examine.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr.huang
 */
@Service
public class UserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherDao teacherDao;

    public Result<String> login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo==null){
            return Result.error(CodeMsg.NULLPOINT);
        }

        String identity = loginVo.getIdentity();

        User user = null;
        if(identity.equals("student")){
            user = studentService.getStudentByStudentID(loginVo.getUsername());
        }else if(identity.equals("teacher")){
            user = teacherDao.getTeacherByTeacherID(loginVo.getUsername());
        }
        if(user == null){
            return Result.error(CodeMsg.USERNAME_ERROR);
        }
        String formPass = loginVo.getPassword();
        String passDB = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        String token = UUIDUtil.uuid();
        if(identity.equals("student")){
            addCookie(response, token, user);
        }else if(identity.equals("teacher")){
            addTeacherCookie(response, token, user);
        }
        if(calcPass.equals(passDB)){
            return Result.OK();
        }else {
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
    }


    public Student getStudentByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        Student student = redisService.get(StudentKey.token, token, Student.class);
        //延长有效期
        if(student != null) {
            addCookie(response, token, student);
        }
        return student;
    }

    public Teacher getTeacherByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        Teacher teacher = redisService.get(TeacherKey.token, token, Teacher.class);
        //延长有效期
        if(teacher != null) {
            addCookie(response, token, teacher);
        }
        return teacher;
    }


    public void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(StudentKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(StudentKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    private void addTeacherCookie(HttpServletResponse response, String token, User user) {
        redisService.set(TeacherKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(TeacherKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
