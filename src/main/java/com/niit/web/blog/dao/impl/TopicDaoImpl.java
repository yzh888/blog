package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.TopicDao;
import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDaoImpl implements TopicDao {
    private Logger logger = LoggerFactory.getLogger(TopicDao.class);

    @Override
    public int[] batchInsert(List<Topic> topicList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_topic(author_id, name, logo, description, update_time, follows, articles) VALUES (?, ?, ?, ?, ?, ?, ?)";
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(sql);
        topicList.forEach(topic -> {
            try {
                pstmt.setLong(1,topic.getAuthorId());
                pstmt.setString(2, topic.getName());
                pstmt.setString(3, topic.getLogo());
                pstmt.setString(4, topic.getDescription());
                pstmt.setObject(5, topic.getUpdateTime());
                pstmt.setInt(6,topic.getFollows());
                pstmt.setLong(7,topic.getArticles());
                pstmt.addBatch();
            } catch (SQLException e) {
                logger.error("专题获取失败");
            }
        });
        int[] n = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null, pstmt, connection);
        return n;
    }

    @Override
    public List<Topic> selectAll() throws SQLException {
        Connection connection = DbUtil.getConnection();
        List<Topic> topicList = new ArrayList<>();
        String sql = "SELECT * FROM t_topic ORDER BY id DESC";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Topic topic = new Topic();
            topic.setId(rs.getInt("id"));
            topic.setAuthorId(rs.getLong("author_id"));
            topic.setName(rs.getString("name"));
            topic.setDescription(rs.getString("description"));
            topic.setLogo(rs.getString("logo"));
            topic.setArticles(rs.getLong("articles"));
            topic.setFollows(rs.getInt("follows"));
            topic.setUpdateTime(rs.getDate("update_time").toLocalDate());
            topicList.add(topic);
        }
        return topicList;
    }
}