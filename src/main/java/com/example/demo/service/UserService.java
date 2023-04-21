package com.example.demo.service;


import com.example.demo.model.entity.Stu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/2/17 10:55
 */

public interface UserService {

    public List<Stu> selAll();

    public List<Stu> selAll2();


}
