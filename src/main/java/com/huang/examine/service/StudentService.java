package com.huang.examine.service;

import com.huang.examine.dao.StudentDao;
import com.huang.examine.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements StudentDao {

    @Autowired
    private StudentDao studentDao;
    @Override
    public Student getStudentById(Integer id) {
        return studentDao.getStudentById(id);
    }
}
