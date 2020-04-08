package com.huang.examine.controller;

import com.huang.examine.entity.*;
import com.huang.examine.entityvo.ExamVo;
import com.huang.examine.redis.RedisService;
import com.huang.examine.service.*;
import com.huang.examine.utils.GlobalUserGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @Autowired
    private PageService pageService;

    @Autowired
    private ChooseService chooseService;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private ExamResultService examResultService;

    /**
     * 学生考试清单
     * 已经结束的列表
     * 即将开始的（考试前五分钟会开启倒计时，时间到达之前点击button无法进入考试）
     * */
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
            boolean examStatus = examService.getExamStatus(student.getId(),exam.getId());
            ExamVo examVo = new ExamVo(exam,remainSeconds,examStatus);
            examVos.add(examVo);
        }
        model.addAttribute("examList",examVos);
        return "examStudent";
    }
    /**
     * 跳转到考试前准备界面
     * 展示本场考试以及考生的基本信息
     * 展示考试须知
     * */
    @RequestMapping(value = "/startExam")
    public String startExam(HttpServletRequest request, HttpServletResponse response, Model model, User user){
        if(user==null){
            return "login";
        }
        Student student = (Student) user;
        Integer examId = Integer.valueOf(request.getParameter("examId"));
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(exam.getPageid());
        model.addAttribute("exam",exam);
        model.addAttribute("student",student);
        model.addAttribute("page",page);
        if(model.getAttribute("chooseIsOver") == null){
            model.addAttribute("chooseIsOver",0);
        }
        if(model.getAttribute("judgeIsOver") == null){
            model.addAttribute("judgeIsOver",0);
        }
        return "examWarn";
    }
    /**
     * 选择题考试
     * */
    @RequestMapping(value = "/startChoose")
    public String startChoose(HttpServletRequest request, HttpServletResponse response, Model model, User user
    ){
        if(user==null){
            return "login";
        }
        Student student = (Student) user;
        Integer examId = Integer.valueOf(request.getParameter("examId"));
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(exam.getPageid());
        List<Choose> chooseList = pageService.getChoosesListByPageId(page.getId());
        //当前指针所在位置
        int index = 0;
        Choose choose = chooseList.get(index);
        // hasNext 表示是否还有下一个 有1 无0
        int hasNext = 1;
        if(chooseList.get(1)==null){
            hasNext = 0;
        }
        model.addAttribute("index",index);
        model.addAttribute("hasNext",hasNext);
        model.addAttribute("choose",choose);
        model.addAttribute("exam",exam);
        model.addAttribute("student",student);
        model.addAttribute("page",page);
        return "examChoose";
    }

    @RequestMapping("/choose")
    public String choose(HttpServletRequest request, HttpServletResponse response, Model model, User user
    ){
        if(user==null){
            return "login";
        }
        Student student = (Student) user;
        ExamResult examResult = new ExamResult();
        String studentResponse = request.getParameter("response");
        int hasNext = Integer.valueOf(request.getParameter("hasNext"));
        Integer examId = Integer.valueOf(request.getParameter("examId"));
        int index = Integer.valueOf(request.getParameter("index")) + 1;
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(exam.getPageid());
        List<Choose> chooseList = pageService.getChoosesListByPageId(page.getId());
        examResult.setExamid(examId);
        examResult.setQuestionid(Integer.valueOf(request.getParameter("questionId")));
        examResult.setType(1);
        examResult.setResult(studentResponse);
        examResult.setResponse(chooseList.get(index-1).getResponse());
        examResult.setStudentid(student.getId());
        if(examResult.getResponse().equals(examResult.getResult())){
            examResult.setIstrue(true);
        }else {
            examResult.setIstrue(false);
        }
        examResultService.insert(examResult);
        if(hasNext == 0){
            model.addAttribute("exam",exam);
            model.addAttribute("student",student);
            model.addAttribute("page",page);
            model.addAttribute("chooseIsOver",1);
            return startExam(request,response,model,student);
        }
        Choose choose = chooseList.get(index);
        if(chooseList.size()==index+1){
            hasNext = 0;
        }
        model.addAttribute("choose",choose);
        model.addAttribute("hasNext",hasNext);
        model.addAttribute("index",index);
        model.addAttribute("exam",exam);
        model.addAttribute("student",student);
        model.addAttribute("page",page);
        return "examChoose";
    }

    /**
     * 判断题考试
     * */
    @RequestMapping(value = "/startJudge")
    public String startJudge(HttpServletRequest request, HttpServletResponse response, Model model, User user
    ){
        if(user==null){
            return "login";
        }
        Student student = (Student) user;
        Integer examId = Integer.valueOf(request.getParameter("examId"));
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(exam.getPageid());
        List<Judge> judgeList = pageService.getJudgeListByPageId(page.getId());
        //当前指针所在位置
        int index = 0;
        // hasNext 表示是否还有下一个 有1 无0
        int hasNext = 1;
        if(judgeList.size()==index+1){
            hasNext = 0;
        }
        Judge judge = judgeList.get(index);
        model.addAttribute("index",index);
        model.addAttribute("hasNext",hasNext);
        model.addAttribute("judge",judge);
        model.addAttribute("exam",exam);
        model.addAttribute("student",student);
        model.addAttribute("page",page);
        return "examJudge";
    }

    @RequestMapping("/judge")
    public String judge(HttpServletRequest request, HttpServletResponse response, Model model, User user
    ){
        if(user==null){
            return "login";
        }
        Student student = (Student) user;
        ExamResult examResult = new ExamResult();
        Boolean studentResponse = Boolean.valueOf(request.getParameter("response"));
        int hasNext = Integer.parseInt(request.getParameter("hasNext"));
        Integer examId = Integer.valueOf(request.getParameter("examId"));
        int index = Integer.valueOf(request.getParameter("index")) + 1;
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(exam.getPageid());
        List<Judge> judgeList = pageService.getJudgeListByPageId(page.getId());
        examResult.setExamid(examId);
        examResult.setQuestionid(Integer.valueOf(request.getParameter("questionId")));
        examResult.setType(2);
        examResult.setResult(request.getParameter("response"));
        examResult.setResponse(String.valueOf(judgeList.get(index-1).getResponse()));
        examResult.setStudentid(student.getId());
        if(examResult.getResponse().equals(examResult.getResult())){
            examResult.setIstrue(true);
        }else {
            examResult.setIstrue(false);
        }
        examResultService.insert(examResult);
        if(hasNext == 0){
            model.addAttribute("exam",exam);
            model.addAttribute("student",student);
            model.addAttribute("page",page);
            model.addAttribute("chooseIsOver",1);
            model.addAttribute("judgeIsOver",1);
            return startExam(request,response,model,student);
        }
        Judge judge = judgeList.get(index);
        if(judgeList.size()==index+1){
            hasNext = 0;
        }
        model.addAttribute("judge",judge);
        model.addAttribute("hasNext",hasNext);
        model.addAttribute("index",index);
        model.addAttribute("exam",exam);
        model.addAttribute("student",student);
        model.addAttribute("page",page);
        return "examJudge";
    }

    @RequestMapping("/finishExam")
    public String finishExam(HttpServletRequest request,HttpServletResponse response,Model model,User user){
        if(user==null){
            return "login";
        }
        Student student = (Student) user;
        Integer examId = Integer.valueOf(request.getParameter("examId"));
        Exam exam = examService.getExamById(examId);
        Page page = pageService.findPageById(exam.getPageid());
        int chooseTrueNum = examResultService.getTrueNum(student.getId(),examId,1);
        int judgeTrueNum = examResultService.getTrueNum(student.getId(),examId,2);
        int score = chooseTrueNum * page.getChooseScore() + judgeTrueNum * page.getJudgeScore();
        int result = examService.setScore(score,examId,student.getId());
        if(result !=1 ){
            return "error";
        }
        exam.setStatus(2);
        examService.updateExam(exam);
        return examStudent(request,response,model);
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String updateDataBase(){
//
//        for(int i = 1;i<53;i++){
//            examService.insertPage(i+52,1,i,2);
//        }
//        Choose choose = new Choose();
//        choose.setQuestion("下面有关JVM内存，说法错误的是？");
//        choose.setResponsea("<pre>程序计数器是一个比较小的内存区域，用于指示当前线程所执行的字节码执行到了第几行，是线程隔离的</pre>");
//        choose.setResponseb("<pre>虚拟机栈描述的是Java方法执行的内存模型，用于存储局部变量，操作数栈，动态链接，方法出口等信息，是线程隔离的</pre>");
//        choose.setResponsec("<pre>方法区用于存储JVM加载的类信息、常量、静态变量、以及编译器编译后的代码等数据，是线程隔离的</pre>");
//        choose.setResponsed("<pre>原则上讲，所有的对象都在堆区上分配内存，是线程之间共享的</pre>");
//        choose.setResponse("D");
//        Judge judge = new Judge();
//        judge.setQuestion("java是世界上最好的语言？");
//        for(int i = 1;i<=50;i++){
//            judge.setId(i+2);;
//            judge.setResponse(true);
//            judgeService.insert(judge);
//        }

        return "OK";
    }
}
