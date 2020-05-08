package com.huang.examine.service;

import com.huang.examine.dao.ExamDao;
import com.huang.examine.dao.SpecialtyDao;
import com.huang.examine.entity.Exam;
import com.huang.examine.entity.Specialty;
import com.huang.examine.entity.Student;
import com.huang.examine.entityvo.StudentExamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/24 14:46
 */
@Component
@Service
public class ExamService implements ExamDao {
    @Autowired
    private ExamDao examDao;

    @Autowired
    private SpecialtyDao specialtyDao;

    @Override
    public List<Exam> getExamByStudentId(Integer userId, Integer type) {
        return examDao.getExamByStudentId(userId,type);
    }

    @Override
    public Exam getExamById(Integer examId) {
        return examDao.getExamById(examId);
    }

    @Override
    public List<Exam> getExamOver(Integer userId,Integer type) {
        return examDao.getExamOver(userId,type);
    }

    @Override
    public int insertPage(Integer id, Integer pageId, Integer questionId, Integer type) {
        return examDao.insertPage(id,pageId,questionId,type);
    }

    @Override
    public int setScore(Integer score, Integer examId, Integer userId) {
        return examDao.setScore(score,examId,userId);
    }

    @Override
    public int getScore(Integer examId, Integer userId) {
        return examDao.getScore(examId,userId);
    }

    @Override
    public int updateExam(Exam exam) {
        return examDao.updateExam(exam);
    }

    @Override
    public boolean getExamStatus(Integer studentId, Integer examId) {
        return examDao.getExamStatus(studentId,examId);
    }

    @Override
    public List<Exam> getExamByTeacherId(Integer id) {
        return examDao.getExamByTeacherId(id);
    }

    @Override
    public int deleteByExamId(Integer examId) {
        return examDao.deleteByExamId(examId);
    }

    @Override
    public int deleteUserExamByEaxmId(Integer id, Integer examId) {
        return examDao.deleteUserExamByEaxmId(id,examId);
    }

    @Override
    public int addExam(Exam exam) {
         return examDao.addExam(exam);
    }

    @Override
    public int insertUserExam(Integer examId, Integer userId, Integer type) {
        return examDao.insertUserExam(examId,userId,type);
    }

    @Override
    public List<Exam> getTeacherExamOver(Integer teacherId) {
        return examDao.getTeacherExamOver(teacherId);
    }

    @Override
    public List<StudentExamVo> getStudentVoByExamId(Integer examId) {
        return examDao.getStudentVoByExamId(examId);
    }
    /**
     * 将specialty信息注入进去
     * */
    public List<StudentExamVo> getStudentVo(Integer examId) {
        List<StudentExamVo> examVos = getStudentVoByExamId(examId);
        for (StudentExamVo studentVo:examVos) {
            Specialty specialty = specialtyDao.getSpecialtyById(studentVo.getSpecialtyId());
            studentVo.setSpecialty(specialty);
        }
        return examVos;
    }
    /**
     * 将考试添加后并对userexam表进行更新
     * */
    public int insertUser(Exam exam,Integer userid,Integer type){
        examDao.addExam(exam);
        int id = exam.getId();
        return insertUserExam(id,userid,type);
    }

}
