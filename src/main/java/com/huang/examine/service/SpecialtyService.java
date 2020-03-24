package com.huang.examine.service;

import com.huang.examine.dao.SpecialtyDao;
import com.huang.examine.entity.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/20 14:40
 */
@Service
public class SpecialtyService implements SpecialtyDao {
    @Autowired
    SpecialtyDao specialtyDao;

    @Override
    public Specialty getByUserId(Integer id) {
        return specialtyDao.getByUserId(id);
    }

    @Override
    public Integer getIdBySpecialty(Specialty specialty) {
        return specialtyDao.getIdBySpecialty(specialty);
    }
}
