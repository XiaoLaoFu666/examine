package com.huang.examine.service;

import com.huang.examine.dao.StudentDao;
import com.huang.examine.entity.LoginVo;
import com.huang.examine.entity.Student;

import com.huang.examine.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class StudentService implements StudentDao {

    @Autowired
    private StudentDao studentDao;
    @Override
    public Student getStudentById(Integer id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public Student getStudentByStudentID(Integer studentId) {
        return studentDao.getStudentByStudentID(studentId);
    }

}
