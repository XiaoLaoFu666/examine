package com.huang.examine.dao;

import com.huang.examine.entity.Student;
import com.huang.examine.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/5 20:49
 */
@Component
@Mapper
public interface SubjectDao {

    @Select("select name from subject where collogeName = #{collogeName} and majorName = #{majorName}")
    List<String> getSubject(String collogeName,String majorName);

    @Select("select id from subject where name = #{name}")
    int getSubjectIdByName(String name);

    /**
     * 获取当前教师所有的课程科目信息
     * */
    @Select("select name from subject where id in (select id from sbjther where teacherId = #{teacherId})")
    List<String> getSubjectListByTeacherId(Integer teacherId);

    /**
     * 获取当前教师科目对应的学生班级
     * */
    @Select("select id from student where specialtyId in (select specialtyId from sbjther where teacherId = #{teacherId} and subjectId = #{subjectId})")
    List<Integer> getStudentIdByTeacherAndSubjectId(Integer teacherId,Integer subjectId);

    @Select("select * from subject where id in (select subjectId from sbjther where teacherId = #{id})")
    List<Subject> getSubjectList(Integer id);

    @Select("select name from subject where id = #{subjectId}")
    String getNameById(Integer subjectId);
}
