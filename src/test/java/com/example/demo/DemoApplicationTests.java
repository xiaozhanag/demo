package com.example.demo;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.DigestUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    final String ApiUrl = "http://117.34.118.185:8081/api/v2/sms/bathSend";
    final String userId = "300153";
    final String apikey = "e9c2314c35a94efd8acf556fd8202824";

    @Test
    void contextLoads() {
        //当前时间毫秒
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, String> map = new HashMap<>();
        map.put("phones", "13727272727");
        map.put("smsContent", "s");
        map.put("sendtime", time );
        map.put("sendtermid", "");
        map.put("customTaskid", "");

        HttpResponse response = HttpRequest.post(ApiUrl)
                .header("userId", userId)
                .header("timeStamp",time)
                .header("token",  DigestUtils.md5DigestAsHex((userId+time+apikey).getBytes()))
                .body(JSON.toJSONString(map))
                .execute();
        System.out.println("请求参数：" + JSON.toJSONString(map));
        String body = response.body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        System.out.println("返回信息：" + jsonObject);

    }


    //public static void main(String[] args) {
    //    List<List<Integer>> lists = groupByThree(1101, 1148);
    //    System.out.println(lists.toString());
    //}

    public static List<List<Integer>> groupByThree(int start, int end) {
        List<List<Integer>> result = new ArrayList<>();

        int index = start;
        while (index < end) {
            List<Integer> group = new ArrayList<>();
            for (int i = 0; i < 3 && index < end; i++, index++) {
                group.add(index);
            }
            result.add(group);
        }

        return result;
    }

    public static void main(String[] args) {
        // 获取当前日期并转成“yyyy-MM-dd”格式
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strCurrentDate = sdf.format(currentDate);
        System.out.println("当前日期为：" + strCurrentDate);

        // 获取当前日期加一天并转成“yyyy-MM-dd”格式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1); // 加一天
        Date nextDate = calendar.getTime();
        String strNextDate = sdf.format(nextDate);
        System.out.println("当前日期加一天为：" + strNextDate);

        //给你一个以下示例：
        List<Map<String,String>> listA = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("id", "123");
        map1.put("UNIT_DESC", "吨");
        map1.put("UNIT_CODE", "t");
        Map<String,String> map2 = new HashMap<>();
        map2.put("id", "456");
        map2.put("UNIT_DESC", "米");
        map2.put("UNIT_CODE", "m");
        Map<String,String> map3 = new HashMap<>();
        map3.put("id", "789");
        map3.put("UNIT_DESC", "平方米");
        map3.put("UNIT_CODE", "m2");
        listA.add(map1);
        listA.add(map2);
        listA.add(map3);
        List<Map<String,String>> listB = new ArrayList<>();
        Map<String,String> map4 = new HashMap<>();
        map4.put("target","铝量");
        map4.put("targetUnit","123");
        Map<String,String> map5 = new HashMap<>();
        map5.put("target","铁量");
        map5.put("targetUnit","456");
        Map<String,String> map6 = new HashMap<>();
        map6.put("target","铝液");
        map6.put("targetUnit","123");
        listB.add(map4);
        listB.add(map5);
        listB.add(map6);
        //找出其中的规律 帮我写一个方法我想让生成的结果是：
        //铝量/吨/t
        //铁量/米/m
        //铝液/吨/t
        List<String> strings = generateResults(listA, listB);
        System.out.println(strings);

    }
    public static List<String> generateResults(List<Map<String, String>> listA, List<Map<String, String>> listB) {
        List<String> results = new ArrayList<>();
        Map<String, String> mapA = new HashMap<>();
        for (Map<String, String> map : listA) {
            mapA.put(map.get("id"), map.get("UNIT_DESC") + "/" + map.get("UNIT_CODE"));
        }

        for (Map<String, String> map : listB) {
            String targetUnit = map.get("targetUnit");
            String target = map.get("target");
            String unitDescCode = mapA.get(targetUnit);
            String result = target + "/" + unitDescCode;
            results.add(result);
        }

        return results;
    }


    public static void main2() {
        List<Map<Date,String>> list1 = new ArrayList<>();
        Map<Date,String> map1 = new HashMap<>();
        map1.put(new Date("2023-05-23"), "1");
        Map<Date,String> map2 = new HashMap<>();
        map2.put(new Date("2023-05-24"), "1");
        Map<Date,String> map3 = new HashMap<>();
        map3.put(new Date("2023-05-25"), "1");
        Map<Date,String> map4 = new HashMap<>();
        map4.put(new Date("2023-05-26"), "1");
        Map<Date,String> map5 = new HashMap<>();
        map5.put(new Date("2023-05-27"), "1");
        list1.add(map1);
        list1.add(map2);
        list1.add(map3);
        list1.add(map4);
        list1.add(map5);

        List<Date> list2 = new ArrayList<>();
        list2.add(new Date("2023-05-24"));
        list2.add(new Date("2023-05-25"));


    }




}
