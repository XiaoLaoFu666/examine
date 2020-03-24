package com.huang.examine.service;

import com.huang.examine.dao.ExamDao;
import com.huang.examine.entity.Exam;
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
}
