package com.huang.examine.entity;

/**
 * @author DELL
 */
public class Page {

    private PageQues pageQues;

    public PageQues getPageQues() {
        return pageQues;
    }

    public void setPageQues(PageQues pageQues) {
        this.pageQues = pageQues;
    }

    private Integer totalScore;

    private Integer chooseScore;

    private Integer judgeScore;

    public Integer getChooseScore() {
        return chooseScore;
    }

    public void setChooseScore(Integer chooseScore) {
        this.chooseScore = chooseScore;
    }

    public Integer getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(Integer judgeScore) {
        this.judgeScore = judgeScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column page.Id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column page.pagename
     *
     * @mbggenerated
     */
    private String pagename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column page.pagenum
     *
     * @mbggenerated
     */
    private String pagenum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column page.choosenum
     *
     * @mbggenerated
     */
    private Integer choosenum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column page.judgenum
     *
     * @mbggenerated
     */
    private Integer judgenum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column page.author
     *
     * @mbggenerated
     */
    private String author;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column page.pageinfo
     *
     * @mbggenerated
     */
    private String pageinfo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column page.Id
     *
     * @return the value of page.Id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column page.Id
     *
     * @param id the value for page.Id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column page.pagename
     *
     * @return the value of page.pagename
     *
     * @mbggenerated
     */
    public String getPagename() {
        return pagename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column page.pagename
     *
     * @param pagename the value for page.pagename
     *
     * @mbggenerated
     */
    public void setPagename(String pagename) {
        this.pagename = pagename == null ? null : pagename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column page.pagenum
     *
     * @return the value of page.pagenum
     *
     * @mbggenerated
     */
    public String getPagenum() {
        return pagenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column page.pagenum
     *
     * @param pagenum the value for page.pagenum
     *
     * @mbggenerated
     */
    public void setPagenum(String pagenum) {
        this.pagenum = pagenum == null ? null : pagenum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column page.choosenum
     *
     * @return the value of page.choosenum
     *
     * @mbggenerated
     */
    public Integer getChoosenum() {
        return choosenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column page.choosenum
     *
     * @param choosenum the value for page.choosenum
     *
     * @mbggenerated
     */
    public void setChoosenum(Integer choosenum) {
        this.choosenum = choosenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column page.judgenum
     *
     * @return the value of page.judgenum
     *
     * @mbggenerated
     */
    public Integer getJudgenum() {
        return judgenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column page.judgenum
     *
     * @param judgenum the value for page.judgenum
     *
     * @mbggenerated
     */
    public void setJudgenum(Integer judgenum) {
        this.judgenum = judgenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column page.author
     *
     * @return the value of page.author
     *
     * @mbggenerated
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column page.author
     *
     * @param author the value for page.author
     *
     * @mbggenerated
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column page.pageinfo
     *
     * @return the value of page.pageinfo
     *
     * @mbggenerated
     */
    public String getPageinfo() {
        return pageinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column page.pageinfo
     *
     * @param pageinfo the value for page.pageinfo
     *
     * @mbggenerated
     */
    public void setPageinfo(String pageinfo) {
        this.pageinfo = pageinfo == null ? null : pageinfo.trim();
    }
}