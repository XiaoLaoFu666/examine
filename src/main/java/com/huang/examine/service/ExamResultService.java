package com.huang.examine.service;

import com.huang.examine.dao.ExamResultDao;
import com.huang.examine.entity.ExamResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/30 12:10
 */
@Service
@Component
public class ExamResultService implements ExamResultDao {
    @Autowired
    private ExamResultDao examResultDao;

    @Override
    public int insert(ExamResult examResult) {
        return examResultDao.insert(examResult);
    }

    @Override
    public int getTrueNum(Integer studentId, Integer examId, Integer type) {
        return examResultDao.getTrueNum(studentId,examId,type);
    }
}
