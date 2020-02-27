package com.huang.examine.dao;

import com.huang.examine.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

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
    public Teacher getTeacherByTeacherID(Integer userId);

}
