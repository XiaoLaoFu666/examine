package com.huang.examine.dao;

import com.huang.examine.entity.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/11 11:21
 */
@Mapper
@Component
public interface ExamDao {
    @Select("select * from exam where id in (select exam from userexam where user= #{userId})")
    public List<Exam> getExamByStudentId(Integer userId);
}
