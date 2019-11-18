package com.niit.web.blog.controller;


import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import com.niit.web.blog.dao.UserDao;

import com.niit.web.blog.domain.UserDto;

import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.ServiceFactory;

import com.niit.web.blog.filter.CorsFilter;

import com.niit.web.blog.service.UserService;

import com.niit.web.blog.util.Message;
import com.niit.web.blog.util.ResponseObject;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;



import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.List;
import java.util.Map;



/**

 * @author m
 * @ClassName UserController
 * @Description TODO
 * @Date 15:56 2019/11/9
 * @Version 1.0
 **/

@WebServlet(urlPatterns = {"/sign-in", "/users/*"})
public class UserController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        logger.info("登录用户信息：" + stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        String stl=this.getServletContext().getAttribute("code").toString();
        Map<String, Object> map;
        ResponseObject ro;
        String msg=null;
        if (userDto.getCode().equals(stl)){
       map = userService.signIn(userDto);
        msg = (String) map.get("msg");
            /*case "登录成功":*/
            ro = ResponseObject.success(200, msg, map.get("data"));
        }else {


               /* break;
            case "密码错误":
            case "手机号不存在":
            default:*/
                ro = ResponseObject.success(200, "验证码错误");
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI().trim();
        List<User> userList = null;
        User user = null;

        if(requestPath.equals("/users")){
            /*查询所有用户*/
            userList = userService.listUser();
        }else{
            int position = requestPath.lastIndexOf("/");
            String id = requestPath.substring(position + 1);
            /*进入用户详情页*/
            user = userService.findUserById(Integer.parseInt(id));
        }
        Gson gson = new GsonBuilder().create();
        ResponseObject ro = new ResponseObject();
        ro.setCode(resp.getStatus());
        if(resp.getStatus() == 200){
            ro.setMsg("响应成功");
        }else{
            ro.setMsg("响应失败");
        }

        if(userList!=null) {
            ro.setData(userList);

        }else {
            ro.setData(user);
        }

        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }


}