package com.huang.examine.dao;

import com.huang.examine.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

}
