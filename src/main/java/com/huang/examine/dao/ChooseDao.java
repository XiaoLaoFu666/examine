package com.huang.examine.dao;

import com.huang.examine.entity.Choose;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

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


}
