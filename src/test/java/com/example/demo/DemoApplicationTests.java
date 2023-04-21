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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

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
        map.put("smsContent", "一条测试消");
        map.put("sendtime", "20211127000005");
        map.put("sendtermid", "234");
        map.put("customTaskid", "2343243524353244325");

        HttpResponse response = HttpRequest.post(ApiUrl)
                .header("userId", userId)
                .header("timeStamp", time)
                .header("token", DigestUtils.md5DigestAsHex((userId + time + apikey).getBytes()))
                .body(JSON.toJSONString(map))
                .execute();
        System.out.println("请求参数：" + JSON.toJSONString(map));
        String body = response.body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        System.out.println("返回信息：" + jsonObject);



    }
}
