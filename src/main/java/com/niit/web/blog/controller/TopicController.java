package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.dao.TopicDao;
import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.TopicService;
import com.niit.web.blog.util.DbUtil;
import com.niit.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(urlPatterns = "/topic")
public class TopicController extends HttpServlet {
    private TopicService topicService = ServiceFactory.getTopicServiceInstance();
    private Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Topic> topicList = topicService.listTopic();
        ResponseObject ro = new ResponseObject();
        ro.setCode(resp.getStatus());
        if(resp.getStatus() == 200){
            ro.setMsg("响应成功");
        }else {
            ro.setMsg("响应失败");
        }
        Gson gson = new GsonBuilder().create();
        ro.setData(topicList);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }
}