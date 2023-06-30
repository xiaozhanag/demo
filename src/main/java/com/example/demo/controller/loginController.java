package com.example.demo.controller;

import com.example.demo.model.entity.Stu;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/4/21 16:51
 */
@RestController
@RequestMapping("/api")
public class loginController {

    @PostMapping("/login")
    public String login(@RequestBody Stu stu) {
        if (stu.getUserName().equals("admin") && stu.getPassword().equals("123456")) {
            return "登录成功";
        }
        return "登录失败";
    }


}
