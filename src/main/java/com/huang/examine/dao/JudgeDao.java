package com.huang.examine.dao;

import com.huang.examine.entity.Choose;
import com.huang.examine.entity.Judge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Select("select * from judge where id = #{id}")
    Judge getById(Integer id);

    /**
     * 随机获取指定数目、题型的的题目
     * */
    @Select("SELECT * FROM judge WHERE  subjectId = #{subjectId} and id >= ((SELECT MAX(id) FROM judge)-(SELECT MIN(id) FROM judge)) * RAND() + (SELECT MIN(id) FROM judge) LIMIT #{number}")
    List<Judge> getMockJudges(int subjectId, int number);
}
