package com.huang.examine.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/5 20:49
 */
@Component
@Mapper
public interface SubjectDao {

    @Select("select name from subject where collogeName = #{collogeName} and majorName = #{majorName}")
    List<String> getSubject(String collogeName,String majorName);

    @Select("select id from subject where name = #{name}")
    int getSubjectIdByName(String name);
}
