package com.example.demo;

import javax.xml.transform.Source;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/6/10 19:39
 */
public class test2 {

    public static void main(String[] args) {

        String expression = "(12.3*45.6)/-7.89**3.3+2.2-1.1";
        List<Double> numbers = extractNumbers(expression);
        System.out.println(numbers);

        String dateString = "2023-06-28";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 解析日期字符串为LocalDate对象
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        // 转换为Date类型的日期
        Date date = java.sql.Date.valueOf(localDate);
        // 创建 Calendar 实例
        Calendar calendar = Calendar.getInstance();
        // 设置日期为当前日期
        calendar.setTime(date);
        // 将日期加一天
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        // 获取加一天后的日期
        Date newDate = calendar.getTime();

        long time = newDate.getTime();

        long l = Instant.now().toEpochMilli();
        System.out.println(time);
        System.out.println(l);

    }

    public static List<Double> extractNumbers(String expression) {
        List<Double> numbers = new ArrayList<>();

        // 正则表达式匹配数字（包括整数、小数和负数）
        String regex = "-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        // 循环匹配并转换为 Double 类型
        while (matcher.find()) {
            String numberStr = matcher.group();
            Double number = Double.parseDouble(numberStr);
            numbers.add(number);
        }

        return numbers;
    }
}
