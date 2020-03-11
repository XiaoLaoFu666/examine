package com.huang.examine.service;

import com.huang.examine.dao.TeacherDao;
import com.huang.examine.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.huang
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public Teacher getTeacherByTeacherID(String teacherId){
        return teacherDao.getTeacherByTeacherID(teacherId);
    }



}
