package com.huang.examine.service;

import com.huang.examine.dao.SubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/5 20:53
 */
@Component
@Service
public class SubjectService implements SubjectDao {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public List<String> getSubject(String collogeName, String majorName) {
        return subjectDao.getSubject(collogeName,majorName);
    }

    @Override
    public int getSubjectIdByName(String name) {
        return subjectDao.getSubjectIdByName(name);
    }
}
