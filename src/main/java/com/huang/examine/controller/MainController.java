package com.huang.examine.controller;

import com.huang.examine.entity.Student;
import com.huang.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mr.huang
 * */

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        Student student = studentService.getStudentById(1);
        return "hello examine" + student.getName() + student.getStudentid();
    }

    @RequestMapping("hello")
    public String hello(){
        return "index";
    }

}
