package com.huang.examine.mapper;

import com.huang.examine.entity.Exam;

public interface ExamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam
     *
     * @mbggenerated
     */
    int insert(Exam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam
     *
     * @mbggenerated
     */
    int insertSelective(Exam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam
     *
     * @mbggenerated
     */
    Exam selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Exam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Exam record);
}