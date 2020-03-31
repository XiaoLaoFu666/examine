package com.huang.examine.service;

import com.huang.examine.dao.ChooseDao;
import com.huang.examine.entity.Choose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
