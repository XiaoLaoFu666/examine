package com.huang.examine.controller;

import com.huang.examine.entity.Specialty;
import com.huang.examine.entity.Student;
import com.huang.examine.entity.User;
import com.huang.examine.redis.RedisService;
import com.huang.examine.redis.StudentKey;
import com.huang.examine.result.CodeMsg;
import com.huang.examine.result.Result;
import com.huang.examine.service.SpecialtyService;
import com.huang.examine.service.StudentService;
import com.huang.examine.service.UserService;
import com.huang.examine.utils.GlobalUserGet;
import com.huang.examine.utils.MD5Util;
import com.huang.examine.utils.UUIDUtil;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GlobalUserGet globalUserGet;

    @Autowired
    private SpecialtyService specialtyService;


    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model, User user){
        if(user==null){
            return "login";
        }
        Student student = (Student) user;
        model.addAttribute("student",student);
        return "userIndex";
    }
    @RequestMapping("/info")
    public String info(HttpServletRequest request,HttpServletResponse response,Model model,User user){
        if(user==null){
            return "login";
        }
        Student student = (Student) user;
        model.addAttribute("student",student);
        return "studentInfo";
    }


    @Transactional
    @RequestMapping("/changeInfo")
    public String changeInfo(HttpServletRequest request,HttpServletResponse response,Model model,User user) {
        if(user==null){
            return "login";
        }
        Student student = (Student) user;
        student.setName(request.getParameter("name"));
        Specialty specialty = new Specialty();
        specialty.setCollege(request.getParameter("college"));
        specialty.setGrade(request.getParameter("grade"));
        specialty.setMajor(request.getParameter("major"));
        specialty.setClassname(request.getParameter("classname"));
        int id= specialtyService.getIdBySpecialty(specialty);
        student.setSpecialtyId(id);
        int result = studentService.updateInfo(student);
        if(result!=1){
            return "error";
        }
        String token = UUIDUtil.uuid();
        redisService.set(StudentKey.token,token,student);
        userService.addCookie(response, token, student);
        model.addAttribute("student",student);
        return "studentInfo";
    }

    @Transactional
    @RequestMapping("/changePass")
    @ResponseBody
    public Result<String> changePass(HttpServletRequest request,HttpServletResponse response,Model model,User user){
        if(user==null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        Student student = (Student) user;
        String password = student.getPassword();
        String newPassword = request.getParameter("newPassword");
        String originalPassword = request.getParameter("originalPassword");
        String saltDB = student.getSalt();
        String calcPass = MD5Util.formPassToDBPass(originalPassword, saltDB);
        System.out.println(calcPass);
        if(!calcPass.equals(password)){
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
        student.setPassword(MD5Util.formPassToDBPass(newPassword,saltDB));
        int result = studentService.updateInfo(student);
        if(result!=1){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        String token = UUIDUtil.uuid();
        redisService.set(StudentKey.token,token,student);
        userService.addCookie(response, token, student);
        model.addAttribute("student",student);
        return Result.OK();
    }
}
