package com.example.demo;

import com.example.demo.model.entity.User;
import com.example.demo.util.ObjectDefaultsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/7/7 18:16
 */
public class test4 {

    public static void main(String[] args) {

        User user = null;
        if (user == null){
            user = new User();
            System.out.println(user.getRoleName());
        }

        ObjectDefaultsUtil.setDefaultValues(user);
        System.out.println(user.getRoleName()); // 输出默认值

        List<User> userList = null;
        if (userList == null){
            userList = new ArrayList<>();
            userList.add(user);
        }
        ObjectDefaultsUtil.setDefaultValues(userList);
        System.out.println(userList.get(0).getRoleName()); // 输出默认值

        List<User> userList2 = new ArrayList<>();
        List<String> collect = userList2.stream().map(User::getRoleName).collect(Collectors.toList());
        System.out.println(collect);
    }

}
