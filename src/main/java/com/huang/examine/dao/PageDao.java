package com.huang.examine.dao;

import com.huang.examine.entity.Choose;
import com.huang.examine.entity.Judge;
import com.huang.examine.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/26 17:49
 */
@Mapper
@Component
public interface PageDao {

    /**
     * 通过ID获取试卷信息
     * */
    @Select("select * from page where id = #{id}")
    public Page findPageById(Integer id);

    /**
     * 通过试卷id获取选择题信息
     * */
    @Select(("select * from choose where id in (select questionId from pageques where pageId = #{id} and type = 1)"))
    List<Choose> getChoosesListByPageId(Integer id);

    /**
     * 通过试卷id获取判断题信息
     * */
    @Select("select * from judge where id in (select questionId from pageques where pageId = #{id} and type = 2)")
    List<Judge> getJudgeListByPageId(Integer id);

}
