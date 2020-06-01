package com.huang.examine.dao;

import com.huang.examine.entity.Choose;
import com.huang.examine.entity.Judge;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    @Select("select * from judge where subjectId=#{subjectId} order by rand() limit #{number}")
    List<Judge> getMockJudges(int subjectId, int number);

    @Insert("insert into judge (response, question\n" +
            "   ,subjectId   )\n" +
            "    values ( #{response,jdbcType=BIT}, #{question,jdbcType=LONGVARCHAR}\n" +
            "     , #{subjectId})")
    int addJudge(Judge judge);

    @Select("select * from judge where subjectId in (select subjectId from sbjther where teacherId = #{id}) order by subjectId")
    List<Judge> getJudgeListByAllSubjectId(Integer id);

    @Select("select * from judge where subjectId = #{subjectId}")
    List<Judge> getJudgeListBySubjectId(Integer subjectId);

    @Delete("delete from judge where id = #{judgeId}")
    int deleteById(Integer judgeId);

    @Select("select count(*) from judge")
    int getCount();

    @Select("select * from judge limit #{pageNo},#{pageSize}")
    List<Judge> pageList(Map map);

    @Select("select count(*) from judge where subjectId=#{subjectId}")
    int getSubjectCount(Integer subjectId);

    @Select("select * from judge where subjectId= #{subjectId} limit #{pageNo},#{pageSize}")
    List<Judge> pageSubjectList(Map map);
}
