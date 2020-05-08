package com.huang.examine.entityvo;

import com.huang.examine.entity.Specialty;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/22 13:29
 */
public class StudentExamVo {
    /**
     *学号
     * */
    private String userId;

    private String name;

    private Integer specialtyId;

    private Specialty specialty;

    private String sex;

    private int score;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
