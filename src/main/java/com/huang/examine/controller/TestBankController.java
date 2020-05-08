package com.huang.examine.controller;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/22 16:08
 */

import com.huang.examine.entity.*;
import com.huang.examine.result.CodeMsg;
import com.huang.examine.result.Result;
import com.huang.examine.service.*;
import com.huang.examine.utils.GlobalUserGet;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 题库管理
 * */
@Controller
@RequestMapping("/testBank")
public class TestBankController {


    private static Logger log = LoggerFactory.getLogger(TestBankController.class);


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

    @RequestMapping("/addChoose")
    public String addChoose(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        int type = 1;
        List<Subject> subjectList = subjectService.getSubjectList(teacher.getId());
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("type",type);
        model.addAttribute("teacher",teacher);
        return "addQuestion";
    }
    @RequestMapping("/addChoose/add")
    @ResponseBody
    public Result<String> choose(Choose choose, HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        Teacher teacher = (Teacher) user;
        int result = chooseService.addChoose(choose);
        if(result == 1){
            return Result.OK();
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
    @RequestMapping("/addJudge")
    public String addJudge(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        int type = 2;
        List<Subject> subjectList = subjectService.getSubjectList(teacher.getId());
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("type",type);
        model.addAttribute("teacher",teacher);
        return "addQuestion";
    }
    @RequestMapping("/addJudge/add")
    @ResponseBody
    public Result<String> judge(Judge judge, HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        Teacher teacher = (Teacher) user;
        int result = judgeService.addJudge(judge);
        if(result == 1){
            return Result.OK();
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @RequestMapping("/chooseCheck")
    public String chooseCheck(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        List<Subject> subjectList = subjectService.getSubjectList(teacher.getId());
        List<Choose> chooseList = chooseService.getChooseListByAllSubjectId(teacher.getId());
        String subjectName = "全部科目";
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("chooseList",chooseList);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("teacher",teacher);
        return "chooseCheck";
    }

    @RequestMapping("/chooseCheck/subject")
    public String chooseCheckBySubjectId(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Integer subjectId = Integer.parseInt(request.getParameter("subjectId"));
        List<Subject> subjectList = subjectService.getSubjectList(teacher.getId());
        List<Choose> chooseList = chooseService.getChooseListBySubjectId(subjectId);
        String subjectName = subjectService.getNameById(subjectId);
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("chooseList",chooseList);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("teacher",teacher);
        return "chooseCheck";
    }

    @RequestMapping("/judgeCheck")
    public String judgeCheck(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        List<Subject> subjectList = subjectService.getSubjectList(teacher.getId());
        List<Judge> judgeList = judgeService.getJudgeListByAllSubjectId(teacher.getId());
        String subjectName = "全部科目";
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("judgeList",judgeList);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("teacher",teacher);
        return "judgeCheck";
    }

    @RequestMapping("/judgeCheck/subject")
    public String judgeCheckBySubjectId(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Integer subjectId = Integer.parseInt(request.getParameter("subjectId"));
        List<Subject> subjectList = subjectService.getSubjectList(teacher.getId());
        List<Judge> judgeList = judgeService.getJudgeListBySubjectId(subjectId);
        String subjectName = subjectService.getNameById(subjectId);
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("judgeList",judgeList);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("teacher",teacher);
        return "judgeCheck";
    }

    @RequestMapping("/chooseCheck/delete")
    public String chooseDelete(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Integer chooseId = Integer.parseInt(request.getParameter("chooseId"));
        Choose choose = chooseService.getById(chooseId);
        chooseService.deleteById(chooseId);
        Integer subjectId = choose.getSubjectId();
        List<Subject> subjectList = subjectService.getSubjectList(teacher.getId());
        List<Choose> chooseList = chooseService.getChooseListBySubjectId(subjectId);
        String subjectName = subjectService.getNameById(subjectId);
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("chooseList",chooseList);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("teacher",teacher);
        return "chooseCheck";
    }

    @RequestMapping("/judgeCheck/delete")
    public String judgeDelete(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = globalUserGet.getTeacher(request, response);
        if(user == null){
            return "login";
        }
        Teacher teacher = (Teacher) user;
        Integer judgeId = Integer.parseInt(request.getParameter("judgeId"));
        Judge judge = judgeService.getById(judgeId);
        judgeService.deleteById(judgeId);
        Integer subjectId = judge.getSubjectId();
        List<Subject> subjectList = subjectService.getSubjectList(teacher.getId());
        List<Judge> judgeList = judgeService.getJudgeListBySubjectId(subjectId);
        String subjectName = subjectService.getNameById(subjectId);
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("judgeList",judgeList);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("teacher",teacher);
        return "chooseCheck";
    }
}
