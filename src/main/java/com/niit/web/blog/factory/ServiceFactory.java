package com.niit.web.blog.factory;

import com.niit.web.blog.service.ArticleService;
import com.niit.web.blog.service.StudentService;
import com.niit.web.blog.service.TopicService;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.service.impl.ArticleServiceImpl;
import com.niit.web.blog.service.impl.StudentServiceImpl;
import com.niit.web.blog.service.impl.TopicServiceImpl;
import com.niit.web.blog.service.impl.UserServiceImpl;


/**
 * @author zh_yan
 * @ClassName ServiceFactory
 * @Description TODO
 * @Date 2019/11/5
 * @Version 1.0
 **/
public class ServiceFactory {
    public static StudentService getStudentServiceInstance(){
        return new StudentServiceImpl();
    }
    public static UserService getUserServiceInstance() {
        return new UserServiceImpl();
    }
    public static ArticleService getArticleServiceInstance() {
        return new ArticleServiceImpl();
    }
    public static TopicService getTopicServiceInstance(){ return new TopicServiceImpl(); }
}
