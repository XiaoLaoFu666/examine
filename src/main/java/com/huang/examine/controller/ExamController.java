package com.huang.examine.controller;

import com.huang.examine.entity.Exam;
import com.huang.examine.entity.Student;
import com.huang.examine.entityvo.ExamVo;
import com.huang.examine.redis.RedisService;
import com.huang.examine.service.ExamService;
import com.huang.examine.service.StudentService;
import com.huang.examine.utils.GlobalUserGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/24 11:24
 */
@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private GlobalUserGet globalUserGet;

    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamService examService;

    @RequestMapping("/examStudent")
    public String examStudent(HttpServletRequest request, HttpServletResponse response, Model model){
        Student student = (Student) globalUserGet.getCurrentUser(request,response);
        if(student == null){
            return "login";
        }
        model.addAttribute("student",student);
        List<Exam> examList = examService.getExamByStudentId(student.getId(),1);
        List<ExamVo> examVos = new ArrayList<>();
        for (Exam exam:examList) {
           long startAt = exam.getDate().getTime();
            long endAt = exam.getEndTime().getTime();
            long now = System.currentTimeMillis();
            int remainSeconds;
            if(now < startAt ) {
                //考试尚未开始
                exam.setStatus(1);
                remainSeconds = (int)((startAt - now )/1000);
            }else  if(now > endAt){
                //考试已经结束
                exam.setStatus(3);
                remainSeconds = -1;
            }else{
                //考试正在进行
                exam.setStatus(2);
                remainSeconds = 0;
            }
            ExamVo examVo = new ExamVo(exam,remainSeconds,exam.getStatus());
            examVos.add(examVo);
        }
        model.addAttribute("examList",examVos);
        return "examStudent";
    }

}
