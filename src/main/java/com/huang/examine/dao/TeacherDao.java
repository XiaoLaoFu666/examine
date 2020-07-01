package com.huang.examine.dao;

import com.huang.examine.entity.Subject;
import com.huang.examine.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.huang
 */
@Component
@Mapper
public interface TeacherDao {
    /**
     * @param userId:老师工号
     * @return Teacher
     * */
    @Select("select * from teacher where userId = #{userId}")
    public Teacher getTeacherByTeacherID(String userId);

    /**
     * 获取当前教师所有课程
     * */
    @Select("select * from subject where id in (select subjectId from sbjther where teacherId = #{id})")
    public List<Subject> getSubjectByTeacherId(Integer id);
}
