package com.huang.examine.entity;

import java.util.List;

/**
 * @author DELL
 */
public class Teacher extends User {

    /**
     * 考试列表
     * */
    private List<Exam> examList;

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    public String role;

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.Id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.userId
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.age
     *
     * @mbggenerated
     */
    private Integer age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.college
     *
     * @mbggenerated
     */
    private String college;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.salt
     *
     * @mbggenerated
     */
    private String salt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.img
     *
     * @mbggenerated
     */
    private String img;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.Id
     *
     * @return the value of teacher.Id
     *
     * @mbggenerated
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.Id
     *
     * @param id the value for teacher.Id
     *
     * @mbggenerated
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.userId
     *
     * @return the value of teacher.userId
     *
     * @mbggenerated
     */
    @Override
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.userId
     *
     * @param userId the value for teacher.userId
     *
     * @mbggenerated
     */
    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.password
     *
     * @return the value of teacher.password
     *
     * @mbggenerated
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.password
     *
     * @param password the value for teacher.password
     *
     * @mbggenerated
     */
    @Override
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.name
     *
     * @return the value of teacher.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.name
     *
     * @param name the value for teacher.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.age
     *
     * @return the value of teacher.age
     *
     * @mbggenerated
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.age
     *
     * @param age the value for teacher.age
     *
     * @mbggenerated
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.college
     *
     * @return the value of teacher.college
     *
     * @mbggenerated
     */
    public String getCollege() {
        return college;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.college
     *
     * @param college the value for teacher.college
     *
     * @mbggenerated
     */
    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.title
     *
     * @return the value of teacher.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.title
     *
     * @param title the value for teacher.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.salt
     *
     * @return the value of teacher.salt
     *
     * @mbggenerated
     */
    @Override
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.salt
     *
     * @param salt the value for teacher.salt
     *
     * @mbggenerated
     */
    @Override
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.img
     *
     * @return the value of teacher.img
     *
     * @mbggenerated
     */
    public String getImg() {
        return img;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.img
     *
     * @param img the value for teacher.img
     *
     * @mbggenerated
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}