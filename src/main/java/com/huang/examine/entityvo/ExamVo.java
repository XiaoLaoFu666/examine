package com.huang.examine.entityvo;

import com.huang.examine.entity.Exam;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/24 13:54
 */
public class ExamVo {

    public Exam exam;

    public long timeLeft;

    public boolean status;

    public int score;

    //考试总分
    public int totalScore;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ExamVo(){};

    public ExamVo(Exam exam, int remainSeconds, boolean status) {
        this.exam = exam;
        this.timeLeft = remainSeconds;
        this.status = status;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
