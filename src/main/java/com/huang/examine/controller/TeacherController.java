package com.huang.examine.controller;

import com.huang.examine.service.TeacherService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mr.huang
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {


    private static Logger log = LoggerFactory.getLogger(TeacherController.class);


    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "Hello Teacher";
    }

}
