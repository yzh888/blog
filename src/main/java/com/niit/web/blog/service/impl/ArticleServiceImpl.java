package com.niit.web.blog.service.impl;

import com.niit.web.blog.dao.ArticleDao;
import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = DaoFactory.getArticleInstance();
    private Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Override
    public List<Article> listArticle() {
        List<Article> articleList = null;
        try {
            articleList = articleDao.selectAll();
        } catch (SQLException e) {
            logger.error("查询所有文章信息出现异常");
        }

        return articleList;
    }

    @Override
    public ArticleVo AuthorArticle(long id) {
        ArticleVo articleVo = null;
        try {
            articleVo = articleDao.selectAuthorArticle(id);
        } catch (SQLException e) {
            logger.error("查询登录用户文章信息异常");
        }
        return articleVo;
    }

    @Override
    public List<Article> listArticleByTitle(String titleName) {
        List<Article> articleList = null;
        try {
            articleList = articleDao.getArticleByTitle(titleName);
        } catch (SQLException e) {
            logger.error("根据标题查询文章异常");
        }
        return articleList;
    }
}