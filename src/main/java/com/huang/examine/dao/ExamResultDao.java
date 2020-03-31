package com.huang.examine.dao;

import com.huang.examine.entity.ExamResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/30 12:08
 */

@Mapper
@Component
public interface ExamResultDao {

    @Insert("insert into examresult (studentId, examId, \n" +
            "      questionId, type, response, \n" +
            "      result, istrue)\n" +
            "    values ( #{studentid,jdbcType=INTEGER}, #{examid,jdbcType=INTEGER}, \n" +
            "      #{questionid,jdbcType=INTEGER}, #{type,jdbcType=INTEGER}, #{response,jdbcType=VARCHAR}, \n" +
            "      #{result,jdbcType=VARCHAR}, #{istrue,jdbcType=TINYINT})")
    public int insert(ExamResult examResult);

    @Select("select count(*) from examresult where studentId= #{studentId} and examId = #{examId} and type = #{type} and istrue = true")
    int getTrueNum(Integer studentId,Integer examId,Integer type);

}
