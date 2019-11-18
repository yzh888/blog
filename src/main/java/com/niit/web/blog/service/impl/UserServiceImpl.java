package com.niit.web.blog.service.impl;

import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.domain.UserDto;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.util.Message;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zh_yan
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public Map<String, Object> signIn(UserDto userDto) {
        User user = null;
        Map<String, Object> map = new HashMap<>();
        try {
            user = userDao.findUserByMobile(userDto.getMobile());
        } catch (SQLException e) {
            logger.error("根据手机号查询用户出现异常");
        }
        if (user != null) {
            if (user.getPassword().equals(userDto.getPassword())) {
                map.put("msg", Message.SIGN_IN_SUCCESS);
                map.put("data", user);
            } else {
                map.put("msg", Message.PASSWORD_ERROR);
            }
        } else {
            map.put("msg", Message.MOBILE_NOT_FOUND);
        }
        return map;
    }

    @Override
    public List<User> listUser() {
        List<User> userList = null;
        try {
            userList = userDao.selectAll();
        } catch (SQLException e) {
            logger.error("查询所有用户功能出现异常");
        }
        return userList;
    }

    @Override
    public User findUserById(long id) {
        User user = null;
        try {
            user = userDao.getUserById(id);
        } catch (SQLException e) {
            logger.error("通过id查询用户出现异常");        }
        return user;
    }

}