package com.huang.examine.controller;

import com.huang.examine.entity.*;
import com.huang.examine.entityvo.ExamVo;
import com.huang.examine.service.*;
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

    @Autowired
    private ExamResultService examResultService;

    @Autowired
    private ChooseService chooseService;

    @Autowired
    private JudgeService judgeService;

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

    @RequestMapping("/examReview")
    public String examReview(HttpServletRequest request,HttpServletResponse response,Model model,User user){
        if(user == null){
            return "login";
        }
        Student student = (Student) user;
        Integer examId = Integer.valueOf(request.getParameter("examId"));
        List<ExamResult> examResultList = examResultService.getExamResultByExamId(student.getId(),examId);
        int index = 0;
        int hasNext = 1;
        if(examResultList.size() == index + 1){
            hasNext = 0;
        }
        ExamResult examResult = examResultList.get(index);
        if(examResult.getType() == 1){
            Choose choose = chooseService.getById(examResult.getQuestionid());
            model.addAttribute("choose",choose);
        }else{
            Judge judge = judgeService.getById(examResult.getQuestionid());
            model.addAttribute("judge",judge);
        }
        model.addAttribute("examResult",examResult);
        model.addAttribute("hasNext",hasNext);
        model.addAttribute("index",index);
        return "examReview";
    }

    @RequestMapping("/reviewDetail")
    public String reviewDetail(HttpServletRequest request,HttpServletResponse response,Model model,User user){
        if(user == null){
            return "login";
        }
        Student student = (Student) user;
        Integer examId = Integer.valueOf(request.getParameter("examId"));
        List<ExamResult> examResultList = examResultService.getExamResultByExamId(student.getId(),examId);
        int index = Integer.valueOf(request.getParameter("index")) + 1;
        int hasNext = Integer.valueOf(request.getParameter("hasNext"));
        if(hasNext == 0){
            return index(request,response,model,user);
        }
        if(examResultList.size() == index + 1){
            hasNext = 0;
        }
        ExamResult examResult = examResultList.get(index);
        if(examResult.getType() == 1){
            Choose choose = chooseService.getById(examResult.getQuestionid());
            model.addAttribute("choose",choose);
        }else{
            Judge judge = judgeService.getById(examResult.getQuestionid());
            model.addAttribute("judge",judge);
        }
        model.addAttribute("examResult",examResult);
        model.addAttribute("hasNext",hasNext);
        model.addAttribute("index",index);
        return "examReview";
    }
}
