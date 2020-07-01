package com.huang.examine.service;

import com.huang.examine.dao.JudgeDao;
import com.huang.examine.entity.Judge;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public int addJudge(Judge judge) {
        return judgeDao.addJudge(judge);
    }

    @Override
    public List<Judge> getJudgeListByAllSubjectId(Integer id) {
        return judgeDao.getJudgeListByAllSubjectId(id);
    }

    @Override
    public List<Judge> getJudgeListBySubjectId(Integer subjectId) {
        return judgeDao.getJudgeListBySubjectId(subjectId);
    }

    @Override
    public int deleteById(Integer judgeId) {
        return judgeDao.deleteById(judgeId);
    }

    @Override
    public int getCount() {
        return judgeDao.getCount();
    }

    @Override
    public List<Judge> pageList(Map map) {
        return judgeDao.pageList(map);
    }

    @Override
    public int getSubjectCount(Integer subjectId) {
        return judgeDao.getSubjectCount(subjectId);
    }

    @Override
    public List<Judge> pageSubjectList(Map map) {
        return judgeDao.pageSubjectList(map);
    }
}
