package com.huang.examine.dao;

import com.huang.examine.entity.Exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/11 11:21
 */
@Mapper
@Component
public interface ExamDao {

    @Select("select * from exam where id in (select exam from userexam where user= #{userId} and usertype = #{type}) order by date asc")
    public List<Exam> getExamByStudentId(Integer userId,Integer type);
    /**
     * 通过examId查询考试信息
     * @return Exam
     * */
    @Select("select * from exam where id =#{examId}")
    Exam getExamById(Integer examId);


    /**
     * 查询已完成的考试以及到期的考试
     * */
    @Select("select distinct * from exam where id in (select exam from userexam where user = #{userId} and usertype = #{type}) and status = 2")
    public List<Exam> getExamOver(Integer userId,Integer type);

    @Insert("insert into pageques(id,pageId,questionId,type) values(#{id},#{pageId},#{questionId},#{type})")
    public int insertPage(Integer id,Integer pageId,Integer questionId,Integer type);

    @Update("update userexam set score = #{score} , status = true where user = #{userId} and exam = #{examId} and usertype = 1")
    public int setScore(Integer score,Integer examId,Integer userId);

    @Select("select score from userexam where exam = #{examId} and user = #{userId} and usertype = 1")
    public int getScore(Integer examId,Integer userId);

    @Update("update exam\n" +
            "    set name = #{name,jdbcType=VARCHAR},\n" +
            "      time = #{time,jdbcType=INTEGER},\n" +
            "      date = #{date,jdbcType=TIMESTAMP},\n" +
            "      course = #{course,jdbcType=VARCHAR},\n" +
            "      pageId = #{pageid,jdbcType=INTEGER},\n" +
            "      status = #{status,jdbcType=INTEGER},\n" +
            "      number = #{number,jdbcType=VARCHAR}\n" +
            "    where Id = #{id,jdbcType=INTEGER}")
    int updateExam(Exam exam);

    @Select("select status from userexam where user = #{studentId} and exam = #{examId} and usertype = 1")
    boolean getExamStatus(Integer studentId,Integer examId);



}
