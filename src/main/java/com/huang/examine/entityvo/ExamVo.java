package com.huang.examine.entityvo;

import com.huang.examine.entity.Exam;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/24 13:54
 */
public class ExamVo {

    public Exam exam;

    public long timeLeft;

    public int status;

    public ExamVo(Exam exam, int remainSeconds, Integer status) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
