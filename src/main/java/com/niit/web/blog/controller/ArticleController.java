package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.ArticleService;
import com.niit.web.blog.util.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/article","/article/*"})
public class ArticleController extends HttpServlet {
    ArticleService articleService = ServiceFactory.getArticleServiceInstance();
    ResponseObject ro = new ResponseObject();
    Gson gson = new GsonBuilder().create();
    ArticleVo articleVo = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI().trim();
        if(requestPath.equals("/article")){
            String keywords = req.getParameter("keywords");
            if (keywords != null) {
                getArticleByKeyWords(resp, keywords);
            }
            getArticle(req, resp);
        }else {
            getArticleByAuthorId(req, resp);

        }
    }

    /*查询所有作者*/
    private void getArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Article> articleList = null;
        articleList = articleService.listArticle();
        ro.setCode(resp.getStatus());
        if(resp.getStatus() == 200){
            ro.setMsg("响应成功");
        }else {
            ro.setMsg("响应失败");
        }
        ro.setData(articleList);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();


    }

    private void getArticleByAuthorId(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String requestPath = req.getRequestURI();
        int position = requestPath.lastIndexOf("/");
        String id = requestPath.substring(position + 1);

        /* 两表联查，查询用户对应的文章信息*/
        articleVo = articleService.AuthorArticle(Long.parseLong(id));
        ro.setCode(resp.getStatus());
        if(resp.getStatus() == 200){
            ro.setMsg("请求成功");
        }else {
            ro.setMsg("请求失败");
        }
        ro.setData(articleVo);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }

    private void getArticleByCreateTime(HttpServletRequest req, HttpServletResponse resp){

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void getArticleByKeyWords( HttpServletResponse resp,String keywords) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        List<Article> articleList = articleService.listArticleByTitle(keywords);
        System.out.println(keywords);
        ResponseObject ro = new ResponseObject();
        ro.setCode(resp.getStatus());
        if(resp.getStatus() == 200){
            ro.setMsg("响应成功");
        }else {
            ro.setMsg("响应失败");
        }
        ro.setData(articleList);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }
}