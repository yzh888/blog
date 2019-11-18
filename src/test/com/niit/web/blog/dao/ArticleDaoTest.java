package com.niit.web.blog.dao;

import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.ArticleService;
import com.niit.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ArticleDaoTest {
    private ArticleDao articleDao = DaoFactory.getArticleInstance();
    private Logger logger = LoggerFactory.getLogger(ArticleDaoTest.class);
    private ArticleService service = ServiceFactory.getArticleServiceInstance();

    @Test
    public void batchInsert() throws SQLException {
        int[] n = articleDao.batchInsert(JSoupSpider.getArticles());
        if(n.length!=0){
            logger.info("获取文章数据成功");
            System.out.println(n.length);
        }else {
            logger.error("获取文章失败");
        }
    }

    @Test
    public void selectAuthorArticle() throws SQLException{
        List<ArticleVo> articleVoList = service.listAuthorArticle(93);
        System.out.println(articleVoList.size());

    }



}