package com.niit.web.blog.service;

import com.niit.web.blog.domain.UserDto;
import com.niit.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {
private UserService userService= ServiceFactory.getUserServiceInstance();
    @Test
    public void signIN() {
        Map<String,Object> map =new HashMap<>();
        UserDto userDto=new UserDto("13969274318","39ab767a27e1fc08d77a51370fc05853");
        map=userService.signIn(userDto);
        System.out.println(map.get("msg"));
        System.out.println(map.get("data"));
    }
}