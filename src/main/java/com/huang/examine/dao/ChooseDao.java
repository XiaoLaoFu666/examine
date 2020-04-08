package com.huang.examine.dao;

import com.huang.examine.entity.Choose;
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
public interface ChooseDao {

    @Update(" update choose\n" +
            "    set responseA = #{responsea },\n" +
            "      responseB = #{responseb },\n" +
            "      responseC = #{responsec },\n" +
            "      responseD = #{responsed },\n" +
            "      response = #{response },\n" +
            "      question = #{question}\n" +
            "    where Id = #{id}")
    public int update(Choose choose);
    
    @Insert("insert into choose (Id, responseA, responseB,responseC, responseD, response,question)\n" +
            "values (#{id,jdbcType=INTEGER}, #{responsea}, #{responseb},#{responsec}, #{responsed}, #{response}, #{question})")
    public int setChoose(Choose choose);

    @Select("select * from choose where id = #{id}")
    Choose getById(Integer id);

    /**
     * 随机获取指定数目、题型的的题目
     * */
    @Select("SELECT * FROM choose WHERE  subjectId = #{subjectId} and id >= ((SELECT MAX(id) FROM choose)-(SELECT MIN(id) FROM choose)) * RAND() + (SELECT MIN(id) FROM choose) LIMIT #{number}")
    List<Choose> getMockChooses(int subjectId, int number);
}
