package com.huang.examine.service;

import com.huang.examine.dao.ExamDao;
import com.huang.examine.dao.SubjectDao;
import com.huang.examine.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/5 20:53
 */
@Component
@Service
public class SubjectService implements SubjectDao {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private ExamDao examDao;
    @Override
    public List<String> getSubject(String collogeName, String majorName) {
        return subjectDao.getSubject(collogeName,majorName);
    }

    @Override
    public int getSubjectIdByName(String name) {
        return subjectDao.getSubjectIdByName(name);
    }

    @Override
    public List<String> getSubjectListByTeacherId(Integer teacherId) {
        return subjectDao.getSubjectListByTeacherId(teacherId);
    }

    @Override
    public List<Integer> getStudentIdByTeacherAndSubjectId(Integer teacherId, Integer subjectId) {
        return subjectDao.getStudentIdByTeacherAndSubjectId(teacherId,subjectId);
    }

    @Override
    public List<Subject> getSubjectList(Integer id) {
        return subjectDao.getSubjectList(id);
    }

    @Override
    public String getNameById(Integer subjectId) {
        return subjectDao.getNameById(subjectId);
    }

    /**
     * 教师设置考试之后发布到学生的考试安排中
     * */
    public void setUserExam(Integer teacherId,Integer subjectId,Integer examId){
        List<Integer> studentId = getStudentIdByTeacherAndSubjectId(teacherId,subjectId);
        for (Integer id :studentId) {
            examDao.insertUserExam(examId,id,1);
        }
    }
}
