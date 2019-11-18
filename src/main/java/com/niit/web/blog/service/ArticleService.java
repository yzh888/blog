package com.niit.web.blog.service;

        import com.niit.web.blog.domain.Vo.ArticleVo;
        import com.niit.web.blog.entity.Article;

        import java.util.List;

public interface ArticleService {

    /**
     * 查询所有文章信息
     * @return
     */
     List<Article> listArticle();


    List<ArticleVo> listAuthorArticle(long id);
}