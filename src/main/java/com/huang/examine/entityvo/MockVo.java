package com.huang.examine.entityvo;

import com.huang.examine.entity.Choose;
import com.huang.examine.entity.Judge;
import com.huang.examine.entity.MockResult;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/7 15:29
 */
public class MockVo {

    MockResult mockResult;

    Choose choose;

    Judge judge;

    public MockResult getMockResult() {
        return mockResult;
    }

    public void setMockResult(MockResult mockResult) {
        this.mockResult = mockResult;
    }

    public Choose getChoose() {
        return choose;
    }

    public void setChoose(Choose choose) {
        this.choose = choose;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }
}
