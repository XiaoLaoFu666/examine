package com.huang.examine.entity;

import java.util.Date;

/**
 * @author DELL
 */
public class MockExam {
    /**
     * 正确的数量
     * */
    private Integer trueNumber;

    public Integer getTrueNumber() {
        return trueNumber;
    }

    public void setTrueNumber(Integer trueNumber) {
        this.trueNumber = trueNumber;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mockexam.Id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mockexam.studentId
     *
     * @mbggenerated
     */
    private Integer studentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mockexam.subject
     *
     * @mbggenerated
     */
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mockexam.number
     *
     * @mbggenerated
     */
    private Integer number;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mockexam.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mockexam.createTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mockexam.Id
     *
     * @return the value of mockexam.Id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mockexam.Id
     *
     * @param id the value for mockexam.Id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mockexam.studentId
     *
     * @return the value of mockexam.studentId
     *
     * @mbggenerated
     */
    public Integer getStudentid() {
        return studentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mockexam.studentId
     *
     * @param studentid the value for mockexam.studentId
     *
     * @mbggenerated
     */
    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mockexam.subject
     *
     * @return the value of mockexam.subject
     *
     * @mbggenerated
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mockexam.subject
     *
     * @param subject the value for mockexam.subject
     *
     * @mbggenerated
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mockexam.number
     *
     * @return the value of mockexam.number
     *
     * @mbggenerated
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mockexam.number
     *
     * @param number the value for mockexam.number
     *
     * @mbggenerated
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mockexam.type
     *
     * @return the value of mockexam.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mockexam.type
     *
     * @param type the value for mockexam.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mockexam.createTime
     *
     * @return the value of mockexam.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mockexam.createTime
     *
     * @param createtime the value for mockexam.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}