package com.huang.examine.dao;

import com.huang.examine.entity.Specialty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/8 17:04
 */
@Mapper
@Component
public interface SpecialtyDao {
    /**
     * 通过用户id查找专业信息
     * */
    @Select("select * from specialty where id = #{id}")
    public Specialty getByUserId(Integer id);
}
