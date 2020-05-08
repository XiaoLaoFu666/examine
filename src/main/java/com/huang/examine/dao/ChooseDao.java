package com.huang.examine.dao;

import com.huang.examine.entity.Choose;
import org.apache.ibatis.annotations.*;
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
    @Select("select * from choose where subjectId=#{subjectId} order by rand() limit #{number}")
    List<Choose> getMockChooses(int subjectId, int number);
    @Insert(" insert into choose (responseA, responseB, \n" +
            "      responseC, responseD, response, \n" +
            "      question,subjectId)\n" +
            "    values (#{responsea,jdbcType=VARCHAR}, #{responseb,jdbcType=VARCHAR}, \n" +
            "      #{responsec,jdbcType=VARCHAR}, #{responsed,jdbcType=VARCHAR}, #{response,jdbcType=VARCHAR}, \n" +
            "      #{question,jdbcType=LONGVARCHAR},#{subjectId})")
    int addChoose(Choose choose);

    @Select("select * from choose where subjectId in (select subjectId from sbjther where teacherId = #{id}) order by subjectId")
    List<Choose> getChooseListByAllSubjectId(Integer id);

    @Select("select * from choose where subjectId = #{subjectId}")
    List<Choose> getChooseListBySubjectId(Integer subjectId);

    @Delete("delete from choose where id = #{chooseId}")
    void deleteById(Integer chooseId);
}
