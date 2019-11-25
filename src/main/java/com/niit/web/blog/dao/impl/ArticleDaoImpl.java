package com.niit.web.blog.dao.impl;
import com.niit.web.blog.dao.ArticleDao;
import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {

    private Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);
    @Override
    public List<Article> selectAll() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_article ORDER BY forward_account DESC";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Article> articleList = new ArrayList<>();
        while (rs.next()){
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setAuthorId(rs.getLong("author_id"));
            article.setTitle(rs.getString("title"));
            article.setDescription(rs.getString("description"));
            article.setContent(rs.getString("content"));
            article.setAvatar(rs.getString("avatar"));
            article.setCommentAccount(rs.getInt("comment_account"));
            article.setLikeAccount(rs.getInt("like_account"));
            article.setForwardAccount(rs.getInt("forward_account"));
            article.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            articleList.add(article);
        }
        return articleList;
    }

    @Override
    public int[] batchInsert(List<Article> articleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_article (author_id, title, description, content, avatar, " +
                "comment_account, like_account, forward_account, create_time) VALUES (?,?,?,?,?,?,?,?,?)";
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(sql);
        articleList.forEach(article -> {
            try {
                pstmt.setLong(1,article.getAuthorId());
                pstmt.setString(2,article.getTitle());
                pstmt.setString(3,article.getDescription());
                pstmt.setString(4,article.getContent());
                pstmt.setString(5,article.getAvatar());
                pstmt.setInt(6,article.getCommentAccount());
                pstmt.setInt(7,article.getLikeAccount());
                pstmt.setInt(8,article.getForwardAccount());
                pstmt.setObject(9,article.getCreateTime());
                pstmt.addBatch();
            } catch (SQLException e) {
                logger.error("文章批量插入出错");
            }
        });
        int [] n = pstmt.executeBatch();
        /*数据库提交*/
        connection.commit();
        DbUtil.close(null,pstmt,connection);
        return n;
    }



    @Override
    public List<Article> getArticleByTitle(String titlename) throws SQLException {
        Connection connection = DbUtil.getConnection();
        List<Article> articleList = new ArrayList<>();
        String sql = "SELECT * FROM t_article WHERE title LIKE  ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + titlename + "%");
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Article article = new Article();
            article.setAuthorId(rs.getLong("author_id"));
            article.setTitle(rs.getString("title"));
            article.setDescription(rs.getString("description"));
            article.setAvatar(rs.getString("avatar"));
            article.setContent(rs.getString("content"));
            article.setForwardAccount(rs.getInt("forward_account"));
            article.setLikeAccount(rs.getInt("like_account"));
            article.setCommentAccount(rs.getInt("comment_account"));
            articleList.add(article);
        }
        return articleList;
    }

    @Override
    public List<Article> getHotArticle() throws SQLException {
        return null;
    }
    @Override
    public ArticleVo selectAuthorArticle(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        /*在文章表和用户表联查，得到结视图对象*/
        String sql = "SELECT a.title, a.comment_account," +
                "a.like_account,a.create_time, a.content, b.nickname, b.avatar\n" +
                "FROM t_article a\n" +
                "LEFT JOIN t_user b\n" +
                "ON a.author_id = b.id\n" +
                "WHERE a.id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        /*对sql语句中的问号进行赋值*/
        pstmt.setLong(1,id);
        ResultSet rs = pstmt.executeQuery();
        ArticleVo articleVo = new ArticleVo();
        while (rs.next()){
            articleVo.setAvatar(rs.getString("avatar"));
            articleVo.setTitle(rs.getString("title"));
            articleVo.setContent(rs.getString("content"));
            articleVo.setCommentAccount(rs.getInt("comment_account"));
            articleVo.setNickname(rs.getString("nickname"));
            articleVo.setLikeAccount(rs.getInt("like_account"));
            articleVo.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
        }
        return articleVo;
    }
}