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
        UserDto userDto=new UserDto("13968448025","1325a31158e65e08158c1fb2e40575c9");
        map=userService.signIn(userDto);
        System.out.println(map.get("msg"));
        System.out.println(map.get("data"));
    }
}