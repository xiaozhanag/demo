package com.example.demo.service.impl;


import com.example.demo.mapper.UserMapper;
import com.example.demo.model.entity.Stu;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/2/17 10:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public List<Stu> selAll() {
        return userMapper.selAll();
    }

    @Override
    public List<Stu> selAll2() {
        return userMapper.selectList(null);
    }
}
