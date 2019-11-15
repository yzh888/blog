package com.niit.web.blog.service;

import com.niit.web.blog.domain.UserDto;

import com.niit.web.blog.entity.User;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Map;

/**
 * @author zh_yan
 * @ClassName UserService
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public interface UserService {
     /**
     * 用户登录功能
     * @param userDto
     * @return
     */



    Map<String, Object> signIn(UserDto userDto);
    List<User> listUser();
}
