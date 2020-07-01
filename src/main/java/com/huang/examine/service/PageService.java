package com.huang.examine.service;

import com.huang.examine.dao.ChooseDao;
import com.huang.examine.dao.JudgeDao;
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

    @Autowired
    private ChooseDao chooseDao;

    @Autowired
    private JudgeDao judgeDao;

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

    @Override
    public void updatePage(Page page) {
        pageDao.updatePage(page);
    }

    @Override
    public int addPage(Page page) {
        return pageDao.addPage(page);
    }

    @Override
    public void addPageQues(Integer pageId, Integer id, int type) {
        pageDao.addPageQues(pageId,id,type);
    }

    @Override
    public void deletePageByPageId(Integer pageid) {
        pageDao.deletePageByPageId(pageid);
    }

    @Override
    public void deletePageQuesByPageId(Integer pageid) {
        pageDao.deletePageQuesByPageId(pageid);
    }

    /**
     * 自动出题保存到pageques中
     * */
    public void setQuestions(Integer pageId,Integer subjectId,Integer chooseNum,Integer judgeNum){
        List<Choose> chooseList = chooseDao.getMockChooses(subjectId,chooseNum);
        List<Judge> judgeList =judgeDao.getMockJudges(subjectId,judgeNum);
        for (Choose choose :chooseList) {
            pageDao.addPageQues(pageId,choose.getId(),1);
        }
        for (Judge judge:judgeList) {
            pageDao.addPageQues(pageId,judge.getId(),2);
        }
    }
}
