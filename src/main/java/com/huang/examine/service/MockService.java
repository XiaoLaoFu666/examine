package com.huang.examine.service;

import com.huang.examine.dao.MockDao;
import com.huang.examine.entity.Choose;
import com.huang.examine.entity.Judge;
import com.huang.examine.entity.MockExam;
import com.huang.examine.entity.MockResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/5 21:54
 */
@Component
@Service
public class MockService implements MockDao{
    @Autowired
    private MockDao mockDao;


    @Override
    public List<MockExam> getMockList(Integer studentId) {
        return mockDao.getMockList(studentId);
    }

    @Override
    public int InsertMockExam(MockExam mockExam) {
        return mockDao.InsertMockExam(mockExam);
    }

    @Override
    public List<MockResult> getResult(Integer id) {
        return mockDao.getResult(id);
    }

    @Override
    public int insertResult(MockResult mockResult) {
        return mockDao.insertResult(mockResult);
    }

    @Override
    public int getTrue(Integer mockId) {
        return mockDao.getTrue(mockId);
    }

    public void setJudges(List<Judge> judgeList, Integer mockId) {
        MockResult mockResult = new MockResult();
        for (Judge judge:judgeList) {
            mockResult.setMockid(mockId);
            mockResult.setQuestionid(judge.getId());
            mockResult.setResponse(String.valueOf(judge.getResponse()));
            mockResult.setType(2);
            mockDao.insertResult(mockResult);
        }
    }

    public void setChooses(List<Choose> chooseList, Integer mockId) {
        MockResult mockResult = new MockResult();
        for (Choose choose:chooseList) {
            mockResult.setMockid(mockId);
            mockResult.setQuestionid(choose.getId());
            mockResult.setResponse(choose.getResponse());
            mockResult.setType(1);
            mockDao.insertResult(mockResult);
        }
    }

    @Override
    public List<MockResult> getResultList(Integer mockId) {
        return mockDao.getResultList(mockId);
    }

    @Override
    public int updateResult(MockResult mockResult) {
            return mockDao.updateResult(mockResult);
    }

    @Override
    public int setTrueNumber(Integer mockId, int trueNumber) {
        return mockDao.setTrueNumber(mockId,trueNumber);
    }

    @Override
    public MockExam getMockExamById(Integer mockId) {
        return mockDao.getMockExamById(mockId);
    }

    /**
     * 批量更新
     * */
    public void updateMockResult(List<MockResult> mockResultList) {
        for (MockResult mockResult:mockResultList) {
            mockDao.updateResult(mockResult);
        }
    }
}
