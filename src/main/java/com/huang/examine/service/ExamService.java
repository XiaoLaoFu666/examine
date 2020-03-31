package com.huang.examine.service;

import com.huang.examine.dao.ExamDao;
import com.huang.examine.entity.Exam;
import com.huang.examine.entity.Student;
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


}
