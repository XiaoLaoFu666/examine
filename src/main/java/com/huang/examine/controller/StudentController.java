package com.huang.examine.controller;

import com.huang.examine.access.AccessInterceptor;
import com.huang.examine.entity.Exam;
import com.huang.examine.entity.Student;
import com.huang.examine.redis.RedisService;
import com.huang.examine.redis.StudentKey;
import com.huang.examine.service.StudentService;
import com.huang.examine.utils.GlobalUserGet;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Mr.huang
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    private static Logger log = LoggerFactory.getLogger(StudentController.class);


    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GlobalUserGet globalUserGet;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        Student student = (Student) globalUserGet.getCurrentUser(request,response);
        model.addAttribute("student",student);
        if(student == null){
            return "login";
        }
        return "userIndex";
    }
    @RequestMapping("info")
    public String info(HttpServletRequest request,HttpServletResponse response,Model model){
        Student student = (Student) globalUserGet.getCurrentUser(request,response);
        model.addAttribute("student",student);
        if(student == null){
            return "login";
        }
        return "studentInfo";
    }
}
