package com.huang.examine.dao;

import com.huang.examine.entity.MockExam;
import com.huang.examine.entity.MockResult;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/5 21:52
 */
@Component
@Mapper
public interface MockDao {

    /**
     * 获取学生所有的模拟考试
     * */
    @Select("select * from mockexam where studentId = #{studentId} order by createTime desc")
    List<MockExam> getMockList(Integer studentId);

    /**
     * 添加模拟考试记录
     * */
    @Insert("insert into mockexam (studentId, subject, \n" +
            "      number, type, createTime)\n" +
            "    values (#{studentid,jdbcType=INTEGER}, #{subject,jdbcType=VARCHAR}, \n" +
            "      #{number,jdbcType=INTEGER}, #{type,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int InsertMockExam(MockExam mockExam);

    /**
     * 根据模拟考试ID获取考试结果
     * */
    @Select("select * from mockresult where mockId = #{id}")
    List<MockResult> getResult(Integer id);

    /**
     * 添加考试结果
     * */
    @Insert("insert into mockresult (mockId, questionId, \n" +
            "      response, result, isTrue, \n" +
            "      type)\n" +
            "    values (#{mockid,jdbcType=INTEGER}, #{questionid,jdbcType=INTEGER}, \n" +
            "      #{response,jdbcType=VARCHAR}, #{result,jdbcType=VARCHAR}, #{istrue,jdbcType=BIT}, \n" +
            "      #{type,jdbcType=INTEGER})")
    int insertResult(MockResult mockResult);

    /**
     * 获取某次模拟考试正确数量
     * */
    @Select("select count(*) from mockresult where mockId = #{mockId} and isTrue= true")
    int getTrue(Integer mockId);

    @Select("select * from mockresult where mockId = #{mockId}")
    List<MockResult> getResultList(Integer mockId);

    @Update("update mockresult\n" +
            "    set mockId = #{mockid,jdbcType=INTEGER},\n" +
            "      questionId = #{questionid,jdbcType=INTEGER},\n" +
            "      response = #{response,jdbcType=VARCHAR},\n" +
            "      result = #{result,jdbcType=VARCHAR},\n" +
            "      isTrue = #{istrue,jdbcType=BIT},\n" +
            "      type = #{type,jdbcType=INTEGER}\n" +
            "    where Id = #{id,jdbcType=INTEGER}")
    int updateResult(MockResult mockResult);


    @Update("update mockexam set trueNumber = #{trueNumber} where id = #{mockId}")
    int setTrueNumber(Integer mockId, int trueNumber);

    @Select("select * from mockexam where id=#{mockId}")
    MockExam getMockExamById(Integer mockId);
}
