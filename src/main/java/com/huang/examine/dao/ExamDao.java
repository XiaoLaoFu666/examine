package com.huang.examine.dao;

import com.huang.examine.entity.Exam;
import com.huang.examine.entity.UserExam;
import com.huang.examine.entityvo.StudentExamVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    @Select("select * from exam where id in(select exam from userexam where user = #{id} and usertype = 2 ) order by date ASC")
    List<Exam> getExamByTeacherId(Integer id);

    @Delete("delete from exam where id = #{examId}")
    int deleteByExamId(Integer examId);

    @Delete("delete from userexam where exam = #{examId} and user = #{id} and usertype = 2")
    int deleteUserExamByEaxmId(Integer id, Integer examId);

    @Insert("insert into exam (name, time, \n" +
            "      date, endTime,course, pageId, \n" +
            "      status, number)\n" +
            "    values (#{name,jdbcType=VARCHAR}, #{time,jdbcType=INTEGER}, \n" +
            "      #{date,jdbcType=TIMESTAMP},#{endTime,jdbcType=TIMESTAMP}, #{course,jdbcType=VARCHAR}, #{pageid,jdbcType=INTEGER}, \n" +
            "      #{status,jdbcType=INTEGER}, #{number,jdbcType=VARCHAR})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int addExam(Exam exam);

    @Insert("insert into userexam (user, exam,usertype,status)\n" +
            "values (#{userId}, #{examId}, #{type},0)")
    int insertUserExam(Integer examId,Integer userId,Integer type);

    /**
     * 查询教师安排的已结束的考试
     * */
    @Select("select distinct * from exam where status = 2 and id in (select exam from userexam where user = #{teacherId} and usertype = 2) order by endTime desc")
    List<Exam> getTeacherExamOver(Integer teacherId);

    /**
     * 通过考试ID查询所有参见该堂考试的学生ID
     * */
    @Select("select userexam.user,userexam.score,student.name,student.specialtyId,student.sex,student.userId from userexam left join student on userexam.user = student.id where userexam.usertype = 1 and userexam.exam = #{examId} order by specialtyId asc,userId asc;")
    List<StudentExamVo> getStudentVoByExamId(Integer examId);

    @Update("update exam set status = 1 where  date > #{date}")
    void updateStatus1(Date date);

    @Update("update exam set status = 3 where  date < #{date} and endTime > #{date}")
    void updateStatus2(Date date);

    @Update("update exam set status = 2 where  endTime< #{date}")
    void updateStatus3(Date date);
}
