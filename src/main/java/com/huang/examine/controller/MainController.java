package com.huang.examine.controller;

import com.huang.examine.entity.LoginVo;
import com.huang.examine.entity.Student;
import com.huang.examine.entity.User;
import com.huang.examine.redis.RedisService;
import com.huang.examine.redis.StudentKey;
import com.huang.examine.redis.TeacherKey;
import com.huang.examine.result.CodeMsg;
import com.huang.examine.result.Result;
import com.huang.examine.service.StudentService;
import com.huang.examine.service.UserService;
import com.huang.examine.utils.GlobalUserGet;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author Mr.huang
 * */

@Controller
@RequestMapping("/")
public class MainController {

    private static Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private GlobalUserGet globalUserGet;

    @Autowired
    private RedisService redisService;


    @RequestMapping
    public String index(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result<String> login(@Valid LoginVo loginVo,HttpServletResponse response){
        log.info(loginVo.toString());
        return userService.login(response,loginVo);
    }

    @RequestMapping("index")
    @ResponseBody
    public String indexC(){
        Student student = studentService.getStudentById(1);
        return "hello examine" + student.getName() + student.getUserId();
    }

    /**
     * 退出登录动作
     * */
    @RequestMapping("/signOut")
    public String signOut(HttpServletResponse response, HttpServletRequest request, User user){
        if(user == null){
            user = globalUserGet.getTeacher(request, response);
            redisService.delete(TeacherKey.token);
        }else {
            redisService.delete(StudentKey.token);
        }

        return "login";
    }

}
