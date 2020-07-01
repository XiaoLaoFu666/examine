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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String subjectName = "全部科目";
        model.addAttribute("subjectName",subjectName);
        String spPage=request.getParameter("pageNumber");
        //设置每页条数
        int pageSize=10;
        //页数
        int pageNo=0;
        if(spPage==null){
            pageNo=1;
        }else {
            pageNo = Integer.valueOf(spPage);
            if (pageNo < 1) {
                pageNo = 1;
            }
        }
        //设置最大页数
        int totalCount=0;
        int count=chooseService.getCount();
        if(count>0){
            totalCount=count;
        }
        int maxPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        if(pageNo>maxPage){
            pageNo=maxPage;
        }
        int tempPageNo=(pageNo-1)*pageSize;
        //计算总数量
        //分页查询
        Map map=new HashMap();
        map.put("pageNo",tempPageNo);
        map.put("pageSize",pageSize);
        List<Choose> chooseList=chooseService.pageList(map);
        //最后把信息放入model转发到页面把信息带过去
        model.addAttribute("chooseList",chooseList);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);
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
        String spPage=request.getParameter("pageNumber");
        //设置每页条数
        int pageSize=10;
        //页数
        int pageNo=0;
        if(spPage==null){
            pageNo=1;
        }else {
            pageNo = Integer.valueOf(spPage);
            if (pageNo < 1) {
                pageNo = 1;
            }
        }
        //设置最大页数
        int totalCount=0;
        int count=judgeService.getSubjectCount(subjectId);
        if(count>0){
            totalCount=count;
        }
        int maxPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        if(pageNo>maxPage){
            pageNo=maxPage;
        }
        int tempPageNo=(pageNo-1)*pageSize;
        //计算总数量
        //分页查询
        Map map=new HashMap();
        map.put("subjectId",subjectId);
        map.put("pageNo",tempPageNo);
        map.put("pageSize",pageSize);
        List<Choose> chooseList=chooseService.pageSubjectList(map);
        //最后把信息放入model转发到页面把信息带过去
        model.addAttribute("chooseList",chooseList);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("teacher",teacher);
        String subjectName = subjectService.getNameById(subjectId);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("subjectName",subjectName);
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
        String subjectName = "全部科目";
        model.addAttribute("subjectName",subjectName);
        String spPage=request.getParameter("pageNumber");
        //设置每页条数
        int pageSize=10;
        //页数
        int pageNo=0;
        if(spPage==null){
            pageNo=1;
        }else {
            pageNo = Integer.valueOf(spPage);
            if (pageNo < 1) {
                pageNo = 1;
            }
        }
        //设置最大页数
        int totalCount=0;
        int count=judgeService.getCount();
        if(count>0){
            totalCount=count;
        }
        int maxPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        if(pageNo>maxPage){
            pageNo=maxPage;
        }
        int tempPageNo=(pageNo-1)*pageSize;
        //计算总数量
        //分页查询
        Map map=new HashMap();
        map.put("pageNo",tempPageNo);
        map.put("pageSize",pageSize);
        List<Judge> judgeList=judgeService.pageList(map);
        //最后把信息放入model转发到页面把信息带过去
        model.addAttribute("judgeList",judgeList);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);
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
        String spPage=request.getParameter("pageNumber");
        //设置每页条数
        int pageSize=10;
        //页数
        int pageNo=0;
        if(spPage==null){
            pageNo=1;
        }else {
            pageNo = Integer.valueOf(spPage);
            if (pageNo < 1) {
                pageNo = 1;
            }
        }
        //设置最大页数
        int totalCount=0;
        int count=judgeService.getSubjectCount(subjectId);
        if(count>0){
            totalCount=count;
        }
        int maxPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        if(pageNo>maxPage){
            pageNo=maxPage;
        }
        int tempPageNo=(pageNo-1)*pageSize;
        //计算总数量
        //分页查询
        Map map=new HashMap();
        map.put("subjectId",subjectId);
        map.put("pageNo",tempPageNo);
        map.put("pageSize",pageSize);
        List<Judge> judgeList=judgeService.pageSubjectList(map);
        //最后把信息放入model转发到页面把信息带过去
        model.addAttribute("judgeList",judgeList);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("teacher",teacher);
        String subjectName = subjectService.getNameById(subjectId);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("subjectName",subjectName);
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
        return chooseCheck(request,response,model);
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
        return judgeCheck(request,response,model);
    }
}
