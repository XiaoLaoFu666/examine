package com.huang.examine.service;

import com.huang.examine.dao.JudgeDao;
import com.huang.examine.entity.Judge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/28 11:37
 */
@Service
public class JudgeService implements JudgeDao {

    @Autowired
    private JudgeDao judgeDao;

    @Override
    public int update(Judge judge) {
        return judgeDao.update(judge);
    }

    @Override
    public int insert(Judge judge) {
        return judgeDao.insert(judge);
    }

    @Override
    public Judge getById(Integer id) {
        return judgeDao.getById(id);
    }

    @Override
    public List<Judge> getMockJudges(int subjectId, int number) {
        return judgeDao.getMockJudges(subjectId,number);
    }
}
