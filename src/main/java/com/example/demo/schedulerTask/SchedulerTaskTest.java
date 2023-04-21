package com.example.demo.schedulerTask;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/3/2 10:08
 */
@Component
@EnableScheduling
public class SchedulerTaskTest {
    private Logger logger= LoggerFactory.getLogger(SchedulerTaskTest.class);
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
    private int count=0;
    private int count2=0;
    @SneakyThrows
    @Scheduled(cron="*/60 * * * * ?")
    @Async("threadPoolTaskExecutor")
    public void process(){
        //Thread.sleep(1000);
        TimeUnit.SECONDS.sleep(1);//秒
        logger.info("英文:this is scheduler task runing "+(count++));
    }

    @Scheduled(fixedRate = 60000)
    @Async("threadPoolTaskExecutor")
    public void currentTime(){
        logger.info("中文:现在时间"+dateFormat.format(new Date())+"--"+(count2++));
    }


}
