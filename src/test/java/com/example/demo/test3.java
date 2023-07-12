package com.example.demo;

import com.example.demo.model.entity.User;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/6/30 18:37
 */
public class test3 {



    public static void main(String[] args) {
        //// 创建线程池，指定线程数量
        //int threadCount = 50;
        //ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        //// 模拟多个并发请求生成单号
        //for (int i = 0; i < threadCount; i++) {
        //    executorService.execute(() -> {
                String orderNumber =getExamNumber();
                System.out.println("单号: " + orderNumber);

                String s = null;
        System.out.println(s);
        //    });
        //}
        //// 关闭线程池
        //executorService.shutdown();
    }

    private static final String DATE_FORMAT = "yyyyMMdd";  // 日期格式
    private static final String INITIAL_SEQUENCE = "001";  // 初始序列号
    private static final AtomicInteger sequence = new AtomicInteger(0);  // 原子递增序列号

    public static String getExamNumber() {
        // 获取当前日期
        String currentDate = getCurrentDate();

        // 获取递增序列
        String sequenceNumber = getNextSequenceNumber();

        // 拼接生成的单号
        return currentDate + "-" + sequenceNumber;
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期的字符串形式
     */
    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(new Date());
    }

    /**
     * 获取递增的序列号
     *
     * @return 递增的三位数序列号
     */
    private static String getNextSequenceNumber() {
        int currentSequence = sequence.incrementAndGet();
        if (currentSequence > 999) {
            // 重置序列为初始值
            sequence.set(1);
            currentSequence = sequence.get();
        }
        // 将序列号格式化为三位数的字符串，保证补零
        return String.format("%03d", currentSequence);
    }
}
