package com.huang.examine.controller;

import com.huang.examine.entity.*;
import com.huang.examine.entityvo.MockVo;
import com.huang.examine.service.ChooseService;
import com.huang.examine.service.JudgeService;
import com.huang.examine.service.MockService;
import com.huang.examine.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.management.VMOptionCompositeData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/5 19:52
 */
@Controller
@RequestMapping("mockExam")
public class MockExamController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private MockService mockService;

    @Autowired
    private ChooseService chooseService;

    @Autowired
    private JudgeService judgeService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model, User user){
        if(user == null){
            return "login";
        }
        Student student = (Student) user;
        List<String> subjectList = subjectService.getSubject(student.getSpecialty().getCollege(),student.getSpecialty().getMajor());
        List<MockExam> mockExams = mockService.getMockList(student.getId());
        if(mockExams.size() == 0){
            model.addAttribute("isNull",1);
        }
        model.addAttribute("mockExams",mockExams);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("student",student);
        return "mockExam";
    }

    @RequestMapping("/createMock")
    public String createMock(HttpServletRequest request,HttpServletResponse response,Model model,User user){
        if(user == null){
            return "login";
        }
        Student student = (Student) user;
        String subject = request.getParameter("subject");
        int subjectId = subjectService.getSubjectIdByName(subject);
        int number = Integer.parseInt(request.getParameter("number"));
        int type = Integer.parseInt(request.getParameter("questionType"));
        Long date = System.currentTimeMillis();
        Date createTime = new Date(date);
        MockExam mockExam = new MockExam();
        mockExam.setSubject(subject);
        mockExam.setCreatetime(createTime);
        mockExam.setNumber(number);
        mockExam.setType(type);
        mockExam.setStudentid(student.getId());
        mockService.InsertMockExam(mockExam);
        System.out.println(mockExam.getId());
        model.addAttribute("mockId",mockExam.getId());
        model.addAttribute("isCheck",0);
        if(type == 1){
            List<Choose> chooseList = chooseService.getMockChooses(subjectId,number);
            mockService.setChooses(chooseList,mockExam.getId());
            model.addAttribute("chooseList",chooseList);
            model.addAttribute("type",1);
            return "mock";
        }
        if(type == 2){
            List<Judge> judgeList = judgeService.getMockJudges(subjectId,number);
            mockService.setJudges(judgeList,mockExam.getId());
            model.addAttribute("judgeList",judgeList);
            model.addAttribute("type",2);
            return "mock";
        }
        return "error";
    }

    @RequestMapping("/mockSubmit")
    public String mockSubmit(HttpServletRequest request, HttpServletResponse response,User user,Model model){
        if(user == null){
            return "login";
        }
        Integer mockId = Integer.parseInt(request.getParameter("mockId"));
        List<MockResult> mockResultList = mockService.getResultList(mockId);
        for (MockResult mockResult:mockResultList) {
            String result = request.getParameter("" + mockResult.getQuestionid());
            mockResult.setResult(result);
            if(result.equals(mockResult.getResponse())){
                mockResult.setIstrue(true);
            }else {
                mockResult.setIstrue(false);
            }
        }
        mockService.updateMockResult(mockResultList);
        int trueNumber = mockService.getTrue(mockId);
        mockService.setTrueNumber(mockId,trueNumber);
        return index(request,response,model,user);
    }

    @RequestMapping("/check")
    public String check(HttpServletRequest request,HttpServletResponse response,Model model,User user){
        if(user == null){
            return "login";
        }
        Student student = (Student) user;
        Integer mockId = Integer.parseInt(request.getParameter("mockId"));
        MockExam mockExam = mockService.getMockExamById(mockId);
        List<MockResult> mockResultList = mockService.getResultList(mockId);
        List<MockVo> mockVoList = new ArrayList<>();
        if(mockExam.getType() == 1){
            for (MockResult mockResult:mockResultList) {
                Choose choose = chooseService.getById(mockResult.getQuestionid());
                MockVo mockVo = new MockVo();
                mockVo.setChoose(choose);
                mockVo.setMockResult(mockResult);
                mockVoList.add(mockVo);
            }
        }else{
            for (MockResult mockResult:mockResultList) {
                Judge judge = judgeService.getById(mockResult.getQuestionid());
                MockVo mockVo = new MockVo();
                mockVo.setJudge(judge);
                mockVo.setMockResult(mockResult);
                mockVoList.add(mockVo);
            }
        }
        model.addAttribute("isCheck",1);
        model.addAttribute("type",mockExam.getType());
        model.addAttribute("mockVoList",mockVoList);
        return "mock";
    }
}
