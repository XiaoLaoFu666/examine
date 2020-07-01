package com.huang.examine.controller;

import com.huang.examine.entity.*;
import com.huang.examine.entityvo.StudentExamVo;
import com.huang.examine.result.Result;
import com.huang.examine.service.*;
import com.huang.examine.utils.DateUtils;
import com.huang.examine.utils.GlobalUserGet;
import com.huang.examine.utils.UpdateUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Mr.huang
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {


    private static Logger log = LoggerFactory.getLogger(TeacherController.class);


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GlobalUserGet globalUserGet;

    @Autowired
    private ExamService examService;

    @Autowired
    private PageService pageService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ChooseService chooseService;

    @Autowired
    private JudgeService judgeService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        List<Subject> subjectList = teacherService.getSubjectByTeacherId(teacher.getId());
        List<Exam> examList = examService.getExamByTeacherId(teacher.getId());
        List<Exam> examProcessing = new ArrayList<>();
        List<Exam> examNotStart= new ArrayList<>();
        for (Exam exam:examList) {
            if(exam.getStatus() == 1){
                examNotStart.add(exam);
            }
            if(exam.getStatus() == 3){
                examProcessing.add(exam);
            }
        }
        model.addAttribute("examNotStart",examNotStart);
        model.addAttribute("examProcessing",examProcessing);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("teacher",teacher);
        return "teacherIndex";
    }

    @RequestMapping("/examCheck")
    public String examCheck(HttpServletRequest request,HttpServletResponse response,Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        List<Exam> examList = examService.getExamByTeacherId(teacher.getId());
        List<Exam> examProcessing = new ArrayList<>();
        List<Exam> examNotStart= new ArrayList<>();
        List<Exam> examEnding = new ArrayList<>();
        for (Exam exam:examList) {
            if(exam.getStatus() == 1){
                examNotStart.add(exam);
            }
            if(exam.getStatus() == 2){
                examEnding.add(exam);
            }
            if(exam.getStatus() == 3){
                examProcessing.add(exam);
            }
        }
        model.addAttribute("examNotStart",examNotStart);
        model.addAttribute("examProcessing",examProcessing);
        model.addAttribute("examEnding",examEnding);
        model.addAttribute("teacher",teacher);
        return "teacherExamCheck";
    }

    /**
     * 查看考试详情
     * */
    @RequestMapping("/examDetail")
    public String examDetail(HttpServletRequest request,HttpServletResponse response ,Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Integer examId = Integer.parseInt(request.getParameter("id"));
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(exam.getPageid());
        List<Choose> chooseList = pageService.getChoosesListByPageId(page.getId());
        List<Judge> judgeList = pageService.getJudgeListByPageId(page.getId());
        model.addAttribute("exam",exam);
        model.addAttribute("page",page);
        model.addAttribute("judgeList",judgeList);
        model.addAttribute("chooseList",chooseList);
        model.addAttribute("teacher",teacher);
        return "examDetail";
    }

    /**
     * 删除考试
     * */
    @Transactional
    @RequestMapping("/examDelete")
    public String examDelete(HttpServletRequest request,HttpServletResponse response,Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Integer examId = Integer.parseInt(request.getParameter("id"));
        Exam exam = examService.getExamById(examId);
        examService.deleteByExamId(examId);
        pageService.deletePageByPageId(exam.getPageid());
        pageService.deletePageQuesByPageId(exam.getPageid());
        examService.deleteUserExamByEaxmId(teacher.getId(),examId);
        return examCheck(request,response,model);
    }

    /**
     * 更新修改考试信息
     * */
    @RequestMapping("/examUpdate")
    public String examUpdate(HttpServletRequest request,HttpServletResponse response,Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Integer examId = Integer.parseInt(request.getParameter("id"));
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(examId);
        List<Choose> chooseList = pageService.getChoosesListByPageId(page.getId());
        List<Judge> judgeList = pageService.getJudgeListByPageId(page.getId());
        model.addAttribute("exam",exam);
        model.addAttribute("page",page);
        model.addAttribute("judgeList",judgeList);
        model.addAttribute("chooseList",chooseList);
        model.addAttribute("teacher",teacher);
        return "examChange";
    }

    /**
     * 修改考试信息
     * */
    @Transactional
    @RequestMapping("/examUpdate/update")
    public String update(HttpServletRequest request,HttpServletResponse response,Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Integer examId = Integer.parseInt(request.getParameter("examId"));
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(examId);
        exam.setTime(Integer.parseInt(request.getParameter("time")));
        exam.setName( request.getParameter("name"));
        String date = request.getParameter("date");
        String endTime = request.getParameter("endTime");
        exam.setDate(DateUtils.LocalDateToDate(date));
        exam.setEndTime(DateUtils.LocalDateToDate(endTime));
        page.setPagename(request.getParameter("pagename"));
        page.setChoosescore(Integer.parseInt(request.getParameter("chooseScore")));
        page.setJudgescore(Integer.parseInt(request.getParameter("judgeScore")));
        page.setTotalscore(page.getChoosescore()*page.getChoosenum() + page.getJudgescore()*page.getJudgenum());
        examService.updateExam(exam);
        pageService.updatePage(page);
        return examCheck(request,response,model);
    }

    /**
     * 添加考试
     * */
    @RequestMapping("/addExam")
    public String addExam(HttpServletResponse response,HttpServletRequest request,Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        List<String> subjectList = subjectService.getSubjectListByTeacherId(teacher.getId());
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("teacher",teacher);
        return "examAdd";
    }

    @Transactional
    @RequestMapping("/addExam/add")
    public String add(HttpServletRequest request,HttpServletResponse response,Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Exam exam = new Exam();
        exam.setName(request.getParameter("name"));
        exam.setTime(Integer.parseInt(request.getParameter("time")));
        exam.setDate(DateUtils.LocalDateToDate(request.getParameter("date")));
        exam.setEndTime(DateUtils.LocalDateToDate(request.getParameter("endTime")));
        exam.setCourse(request.getParameter("subject"));
        exam.setStatus(1);
        exam.setNumber(UpdateUtils.getExamNumber());
        Page page = new Page();
        page.setPageinfo(request.getParameter("pageinfo"));
        page.setPagename(request.getParameter("pagename"));
        page.setJudgenum(Integer.parseInt(request.getParameter("judgenum")));
        page.setJudgescore(Integer.parseInt(request.getParameter("judgeScore")));
        page.setChoosenum(Integer.parseInt(request.getParameter("choosenum")));
        page.setChoosescore(Integer.parseInt(request.getParameter("chooseScore")));
        page.setTotalscore(page.getChoosescore()*page.getChoosenum() + page.getJudgescore()*page.getJudgenum());
        page.setAuthor(teacher.getName());
        page.setPagenum(UpdateUtils.getExamNumber());
        pageService.addPage(page);
        int pageId = page.getId();
        int subjuectId = subjectService.getSubjectIdByName(exam.getCourse());
        pageService.setQuestions(pageId,subjuectId,page.getChoosenum(),page.getJudgenum());
        exam.setPageid(pageId);
        examService.insertUser(exam,teacher.getId(),2);
        Integer examId= exam.getId();
        subjectService.setUserExam(teacher.getId(),subjuectId,examId);
        return examCheck(request,response,model);
    }
    /**
     * 成绩查询
     */
    @RequestMapping("/resultInquiry")
    public String resultInquiry(HttpServletRequest request,HttpServletResponse response,Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        List<String> subjectList = subjectService.getSubjectListByTeacherId(teacher.getId());
        model.addAttribute("teacher",teacher);
        List<Exam> examList = examService.getTeacherExamOver(teacher.getId());
        model.addAttribute("examList",examList);
        return "resultInquiry";
    }

    /**
     * 单趟考试学生成绩查询
     * */
    @RequestMapping("/resultInquiry/check")
    public String check(HttpServletRequest request,HttpServletResponse response,Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Integer examId = Integer.parseInt(request.getParameter("examId"));
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(exam.getPageid());
        List<StudentExamVo> studentExamVos = examService.getStudentVo(examId);
        int totalNumber = studentExamVos.size();
        model.addAttribute("totalNumber",totalNumber);
        model.addAttribute("studentExamVos",studentExamVos);
        model.addAttribute("teacher",teacher);
        model.addAttribute("page",page);
        model.addAttribute("exam",exam);
        return "resultCheck";
    }
}
