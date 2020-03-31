package com.huang.examine.service;

import com.huang.examine.dao.PageDao;
import com.huang.examine.entity.Choose;
import com.huang.examine.entity.Judge;
import com.huang.examine.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/26 17:56
 */
@Service
@Component
public class PageService implements PageDao {
    @Autowired
    private PageDao pageDao;

    @Override
    public Page findPageById(Integer examId) {
        return pageDao.findPageById(examId);
    }

    @Override
    public List<Choose> getChoosesListByPageId(Integer id) {
        return pageDao.getChoosesListByPageId(id);
    }

    @Override
    public List<Judge> getJudgeListByPageId(Integer id) {
        return pageDao.getJudgeListByPageId(id);
    }
}
