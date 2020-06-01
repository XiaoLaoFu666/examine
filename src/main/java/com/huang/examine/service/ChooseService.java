package com.huang.examine.service;

import com.huang.examine.dao.ChooseDao;
import com.huang.examine.entity.Choose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/28 11:36
 */
@Service
public class ChooseService implements ChooseDao {

    @Autowired
    private ChooseDao chooseDao;

    @Override
    public int update(Choose choose) {
        return chooseDao.update(choose);
    }

    @Override
    public int setChoose(Choose choose) {
        return chooseDao.setChoose(choose);
    }

    @Override
    public Choose getById(Integer id) {
        return chooseDao.getById(id);
    }

    @Override
    public List<Choose> getMockChooses(int subjectId, int number) {
        return chooseDao.getMockChooses(subjectId,number);
    }

    @Override
    public int addChoose(Choose choose) {
        return chooseDao.addChoose(choose);
    }

    @Override
    public List<Choose> getChooseListByAllSubjectId(Integer id) {
        return chooseDao.getChooseListByAllSubjectId(id);
    }

    @Override
    public List<Choose> getChooseListBySubjectId(Integer subjectId) {
        return chooseDao.getChooseListBySubjectId(subjectId);
    }

    @Override
    public void deleteById(Integer chooseId) {
        chooseDao.deleteById(chooseId);
    }

    @Override
    public int getCount() {
        return chooseDao.getCount();
    }

    @Override
    public List<Choose> pageList(Map map) {
        return chooseDao.pageList(map);
    }

    @Override
    public int getSubjectCount(Integer subjectId) {
        return chooseDao.getSubjectCount(subjectId);
    }

    @Override
    public List<Choose> pageSubjectList(Map map) {
        return chooseDao.pageSubjectList(map);
    }


}
