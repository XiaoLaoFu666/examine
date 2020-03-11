package com.huang.examine.dao;

import com.huang.examine.entity.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Mr.huang
 */
@Mapper
@Component
public interface StudentDao {

    @Select("select * from student where id = #{id}")

    public Student getStudentById(Integer id);

    @Select("select * from student where userId = #{userId}")
    Student getStudentByStudentID(String userId);
}
