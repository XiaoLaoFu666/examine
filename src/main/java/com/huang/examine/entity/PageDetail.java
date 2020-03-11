package com.huang.examine.entity;

import java.util.List;

/**
 * @author DELL
 */
public class PageDetail {

    private List<Choose> chooseList;

    private  List<Judge> judgeList;

    public List<Choose> getChooseList() {
        return chooseList;
    }

    public void setChooseList(List<Choose> chooseList) {
        this.chooseList = chooseList;
    }

    public List<Judge> getJudgeList() {
        return judgeList;
    }

    public void setJudgeList(List<Judge> judgeList) {
        this.judgeList = judgeList;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pagedetail.Id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pagedetail.pagenum
     *
     * @mbggenerated
     */
    private String pagenum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pagedetail.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pagedetail.questionId
     *
     * @mbggenerated
     */
    private Integer questionid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pagedetail.Id
     *
     * @return the value of pagedetail.Id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pagedetail.Id
     *
     * @param id the value for pagedetail.Id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pagedetail.pagenum
     *
     * @return the value of pagedetail.pagenum
     *
     * @mbggenerated
     */
    public String getPagenum() {
        return pagenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pagedetail.pagenum
     *
     * @param pagenum the value for pagedetail.pagenum
     *
     * @mbggenerated
     */
    public void setPagenum(String pagenum) {
        this.pagenum = pagenum == null ? null : pagenum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pagedetail.type
     *
     * @return the value of pagedetail.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pagedetail.type
     *
     * @param type the value for pagedetail.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pagedetail.questionId
     *
     * @return the value of pagedetail.questionId
     *
     * @mbggenerated
     */
    public Integer getQuestionid() {
        return questionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pagedetail.questionId
     *
     * @param questionid the value for pagedetail.questionId
     *
     * @mbggenerated
     */
    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }
}