package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Topic;

import java.sql.SQLException;
import java.util.List;

public interface TopicDao {

    /**
     * 批量插入专题信息
     * @param topicList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<Topic> topicList) throws SQLException;

    /**
     * 查询所有专题信息
     * @return
     * @throws SQLException
     */
    List<Topic> selectAll() throws SQLException;
}