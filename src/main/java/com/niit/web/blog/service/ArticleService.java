package com.niit.web.blog.service;

import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;

import java.util.List;

public interface ArticleService {

    /**
     * 查询所有文章信息
     * @return
     */
    public List<Article> listArticle();

    /**
     * 两表联查，查询用户对应的文章信息
     * @param id
     * @return
     */
    public List<ArticleVo> listAuthorArticle(long id);
}