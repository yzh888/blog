package com.niit.web.blog.service;


import com.niit.web.blog.entity.Topic;

import java.util.List;

public interface TopicService {

    /**
     * 查询所有专题信息
     * @return
     */
    public List<Topic> listTopic();
}