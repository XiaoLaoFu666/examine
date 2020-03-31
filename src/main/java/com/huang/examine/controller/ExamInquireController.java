package com.huang.examine.controller;

import com.huang.examine.entity.Exam;
import com.huang.examine.entity.Student;
import com.huang.examine.entity.User;
import com.huang.examine.entityvo.ExamVo;
import com.huang.examine.service.ExamService;
import com.huang.examine.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/31 14:07
 * 考试成绩查看模块
 */
@Controller
@RequestMapping("/examInquire")
public class ExamInquireController {

    @Autowired
    private ExamService examService;

    @Autowired
    private PageService pageService;

    @RequestMapping()
    public String index(HttpServletRequest request, HttpServletResponse response, Model model, User user){
        if(user == null){
            return "login";
        }
        Student student = (Student) user;
        List<Exam> examList = examService.getExamOver(student.getId(),1);
        List<ExamVo> examVoList = new ArrayList<>();
        for (Exam exam : examList) {
            ExamVo examVo = new ExamVo();
            examVo.setExam(exam);
            int score = examService.getScore(exam.getId(),student.getId());
            int totalScore = pageService.findPageById(exam.getPageid()).getTotalScore();
            examVo.setScore(score);
            examVo.setTotalScore(totalScore);
            examVo.setStatus(examService.getExamStatus(student.getId(),exam.getId()));
            examVoList.add(examVo);
        }
        model.addAttribute("examVoList",examVoList);
        model.addAttribute("examVList",examVoList);
        model.addAttribute("student",student);
        return "examInquire";
    }

}
