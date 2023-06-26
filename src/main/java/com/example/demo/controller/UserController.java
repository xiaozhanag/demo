package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.entity.Stu;
import com.example.demo.redis.RedisTest;
import com.example.demo.service.UserService;
import com.example.demo.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/2/17 10:54
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    RedisTest redisTest;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @SneakyThrows
    @PostMapping("/sel")
    public List<Stu> selAll() {
        System.out.println("开始");
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(new Thread(() -> {
            //Thread.sleep(10000);
            for (int i = 0; i < 11111; i++) {
                System.out.println("thread1线程执行了");
            }
        }));
        threadPool.execute(new Thread(() -> {
            //Thread.sleep(5000);
            for (int i = 0; i < 11111; i++) {
                System.out.println("thread2线程执行了");
            }
        }));

        System.out.println("完成");
        return userService.selAll();
    }

    @PostMapping("/sel2")
    public List<Stu> selAll2() {

        return userService.selAll2();
    }


    @PostMapping("/selStu/{Id}")
    public Stu selStu(@PathVariable("Id") String Id) {
        //先查redis  key是否存在
        boolean result = redisUtil.hasKey("Stu:" + Id);
        if (result) {
            Object object = redisUtil.get("Stu:" + Id);
            if (object == null) {
                return null;
            }
            return JSON.parseObject(JSON.parse(object.toString()).toString(), Stu.class);
        }
        //没有再查数据库
        Stu stu1 = userMapper.selectById(Id);
        //数据库为空给id的值为空
        if (stu1 == null) {
            redisTest.setValueNull("Stu:" + Id);
            return stu1;
        }
        redisTest.setRandomTime("Stu:" + Id, JSON.toJSONString(stu1));
        return stu1;
    }


}
