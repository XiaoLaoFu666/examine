package com.huang.examine.dao;

import com.huang.examine.entity.Judge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/28 11:25
 */
@Mapper
@Component
public interface JudgeDao {

    @Update("update judge set response = #{response},question = #{question} where Id = #{id}")
    public int update(Judge judge);

    @Insert(" insert into judge (Id, response, question) values (#{id}, #{response}, #{question})")
    public int insert(Judge judge);

}
