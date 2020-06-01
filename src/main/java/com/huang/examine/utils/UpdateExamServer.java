package com.huang.examine.utils;

import com.huang.examine.service.ExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: HuangJunHao
 * @Date: 2020/6/1 10:38
 * 每隔一分钟对数据库exam进行一次更新
 */

@Component
@PropertySource(value = {"classpath:param.properties"},ignoreResourceNotFound=true)
/**
 *获取配置文件中的cron
 */
public class UpdateExamServer {

    private static final Logger log = LoggerFactory.getLogger(UpdateExamServer.class);

    @Autowired
    private ExamService examService;

    @Scheduled(cron ="${cron}")
    public void updateStatusByTime()throws ParseException {

        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // new Date()为获取当前系统时间

        String  s =  df.format(new Date());
        log.info("当前时间s是------："+s);
        //转换成时间戳
        Date date = df.parse(s);

        examService.updateExamStatus(date);

        long time = date.getTime()/1000;
        log.info("当前时间戳ts是-------"+time);

        log.info("更新status成功！");

    }

}
