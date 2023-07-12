package com.example.demo.util;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.mapper.SqlMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/7/11 16:10
 */
public class Test {

    @Autowired
    private SqlMapper sqlMapper;
    /**
     *
     * @param sqlMap map<key  sql>
     * @param params
     * @return map<key  sql值>
     */
    public Map<String, Map<String,String>> selectMapBySQL( Map<String, Map<String,String>>  sqlMap, Map<String, Object> params) {
        Map<String, Map<String,String>> result = new ConcurrentHashMap<>();
        try {
            // 多线程执行多条sql并返回
            result = new ConcurrentHashMap<>();
            Map<String, Map<String,String>> finalResult = result;
            List<Runnable> taskList = new CopyOnWriteArrayList<Runnable>() {
                {
                    for (String key : sqlMap.keySet()) {
                        for (String keyTwo : sqlMap.get(key).keySet()) {
                            if (sqlMap.get(key).get(keyTwo) == null ||  StringUtils.isEmpty(sqlMap.get(key).get(keyTwo))){
                                Map<String, String> map = new HashMap<>();
                                map.put(keyTwo, "0");
                                add(() -> finalResult.put(key, map));
                                continue;
                            }
                            Map<String, String> map2 = new HashMap<>();
                            map2.put(keyTwo, sqlMapper.selectStringBySQL(sqlMap.get(key).get(keyTwo), params));
                            add(() -> finalResult.put(key,map2));
                        }
                    }
                }
            };
            taskList.parallelStream().forEach(Runnable::run);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
