package com.niit.web.blog.service.impl;

import com.niit.web.blog.dao.TopicDao;
import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class TopicServiceImpl implements TopicService {
    private Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);
    private TopicDao topicDao = DaoFactory.getTopicInstance();

    @Override
    public List<Topic> listTopic() {

        List<Topic> topicList = null;
        try {
            topicList = topicDao.selectAll();
        } catch (SQLException e) {
            logger.error("专题遍历异常");
        }
        return topicList;
    }
}