package com.huang.examine.service;

import com.huang.examine.dao.ExamDao;
import com.huang.examine.dao.SpecialtyDao;
import com.huang.examine.dao.StudentDao;
import com.huang.examine.entity.Exam;
import com.huang.examine.entity.Specialty;
import com.huang.examine.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author DELL
 */
@Service
public class StudentService implements StudentDao {

    private static String COOKI_NAME_TOKEN = "token";

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private SpecialtyDao specialtyDao;

    @Autowired
    private ExamDao examDao;
    @Override
    public Student getStudentById(Integer id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public Student getStudentByStudentID(String studentId) {
        Student student = studentDao.getStudentByStudentID(studentId);
        List<Exam> examList = examDao.getExamByStudentId(student.getId());
        student.setExamList(examList);
        Specialty specialty = specialtyDao.getByUserId(student.getSpecialtyId());
        student.setSpecialty(specialty);
        return student;
    }

}
