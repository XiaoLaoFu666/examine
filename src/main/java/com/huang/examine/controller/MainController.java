package com.huang.examine.controller;

import com.huang.examine.entity.Student;
import com.huang.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr.huang
 * */

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private StudentService studentService;

    @RequestMapping
    public String index(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(Integer username,String password,String identity){
        if(identity == "student"){
            return StudentLogin(username,password);
        }
        return "用户不存在!";
    }

    public String  StudentLogin(Integer username,String password){
        Student student = studentService.getStudentByStudentID(username);
        if(student == null){
            return "用户不存在！";
        }
        if(student.getPassword() == password){
            return "登录密码错误！";
        }
        return "登陆成功";
    }

    @RequestMapping("index")
    @ResponseBody
    public String indexC(){
        Student student = studentService.getStudentById(1);
        return "hello examine" + student.getName() + student.getStudentid();
    }


}
