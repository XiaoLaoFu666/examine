package com.huang.examine.service;

import com.huang.examine.dao.TeacherDao;
import com.huang.examine.entity.Subject;
import com.huang.examine.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.huang
 */
@Component
@Service
public class TeacherService implements TeacherDao{

    private static String COOKI_NAME_TOKEN = "token";

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher getTeacherByTeacherID(String teacherId){
        return teacherDao.getTeacherByTeacherID(teacherId);
    }

    @Override
    public List<Subject> getSubjectByTeacherId(Integer id) {
        return teacherDao.getSubjectByTeacherId(id);
    }


}
