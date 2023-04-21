package com.example.demo.redis;

import com.example.demo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/3/2 16:07
 */
@Component
@Slf4j
public class RedisTest {
    //过期时间类型（秒）
    private final TimeUnit timeTypeSecond = TimeUnit.SECONDS;
    //过期时间类型（分）
    private final TimeUnit timeTypeMinute = TimeUnit.MILLISECONDS;
    //过期时间
    private final long time = 10;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    //设置缓存失效时间为随机数（缓存雪崩：redis中大量key集体过期）
    public void setRandomTime(String key,String value){
        //获取随机时间(20-40)
        long second = ThreadLocalRandom.current().nextInt(20, 40 + 1);
        redisTemplate.opsForValue().set(key,value,second,timeTypeSecond);
        log.info("存储数据成功失效时间"+second+"秒");
    }


    //为数据库查询不存在的值设置为null（缓存穿透：大量请求根本不存在的key）
    public void setValueNull(String key){
        redisUtil.set(key,null,time);
        log.info("存储数据null成功失效时间"+time+"秒");
    }


    //热点信息不设置过期时间（缓存击穿：redis中一个热点key过期）
    public void setLengthentime(String key,String value){
        redisUtil.set(key,value,0);
    }

}
