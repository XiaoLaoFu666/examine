package com.huang.examine.dao;

import com.huang.examine.entity.Choose;
import com.huang.examine.entity.Judge;
import com.huang.examine.entity.Page;
import org.apache.ibatis.annotations.*;
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

    @Update(" update page\n" +
            "    set pagename = #{pagename,jdbcType=VARCHAR},\n" +
            "      pagenum = #{pagenum,jdbcType=VARCHAR},\n" +
            "      choosenum = #{choosenum,jdbcType=INTEGER},\n" +
            "      judgenum = #{judgenum,jdbcType=INTEGER},\n" +
            "      author = #{author,jdbcType=VARCHAR},\n" +
            "      totalScore = #{totalscore,jdbcType=INTEGER},\n" +
            "      chooseScore = #{choosescore,jdbcType=INTEGER},\n" +
            "      judgeScore = #{judgescore,jdbcType=INTEGER},\n" +
            "      pageinfo = #{pageinfo,jdbcType=LONGVARCHAR}\n" +
            "    where Id = #{id,jdbcType=INTEGER}")
    void updatePage(Page page);

    @Insert("insert into page (pagename, pagenum, \n"+
                   "      choosenum, judgenum, author, \n"+
                   "      totalScore, chooseScore, judgeScore, \n"+
                   "      pageinfo)\n"+
                   "    values (#{pagename,jdbcType=VARCHAR}, #{pagenum,jdbcType=VARCHAR}, \n"+
                   "      #{choosenum,jdbcType=INTEGER}, #{judgenum,jdbcType=INTEGER}, #{author,jdbcType=VARCHAR}, \n"+
                   "      #{totalscore,jdbcType=INTEGER}, #{choosescore,jdbcType=INTEGER}, #{judgescore,jdbcType=INTEGER}, \n"+
                   "      #{pageinfo,jdbcType=LONGVARCHAR})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int addPage(Page page);

    /**
     * 插入试题详情
     * */
    @Insert(" insert into pageques (pageId, questionId, \n" +
            "      type) values (#{pageId,jdbcType=INTEGER}, #{id,jdbcType=INTEGER}, \n" +
            "      #{type,jdbcType=INTEGER})")
    void addPageQues(Integer pageId, Integer id, int type);

    @Delete("delete from page where id = #{pageId}")
    void deletePageByPageId(Integer pageid);

    @Delete("delete from pageques where pageId=#{pageid}")
    void deletePageQuesByPageId(Integer pageid);
}
