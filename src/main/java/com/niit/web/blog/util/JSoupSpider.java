package com.niit.web.blog.util;

import com.niit.web.blog.entity.Address;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.Jianshu;
import com.niit.web.blog.entity.User;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mq_xu
 * @ClassName JSoupSpider
 * @Description JSoup实现的一个爬虫工具
 * @Date 9:13 2019/11/7
 * @Version 1.0
 **/
public class JSoupSpider {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);

    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(DataUtil.getMobile());
                user.setPassword(DataUtil.getPassword());
                user.setGender(DataUtil.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(DataUtil.getBirthday());
                user.setCreateTime(LocalDateTime.now());
                userList.add(user);
            });
        }
        return userList;
    }



    public static List<Jianshu> getJianshus() {
        Document document = null;
        List<Jianshu> jianshuList = new ArrayList<>(100);

            try {
                document = Jsoup.connect("https://www.jianshu.com/" ).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("have-img");
            divs.forEach(div -> {
                Element wrapImg = div.child(0);
                Element contentDiv = div.child(1);

                Jianshu jianshu = new Jianshu();
                jianshu.setMobile(DataUtil.getMobile());
                jianshu.setPassword(DataUtil.getPassword());
                jianshu.setWriter(contentDiv.child(2).child(1).text());
                jianshu.setTitle(contentDiv.child(0).text());
                jianshu.setText(contentDiv.child(1).text());
                jianshu.setAvatar("https:" + wrapImg.child(0).attr("src"));
                jianshu.setMessage(DataUtil.getMessage());
                jianshu.setAttention(DataUtil.getAttention());

                jianshu.setCreate_time(LocalDateTime.now());
                jianshuList.add(jianshu);
            });

        return jianshuList;
    }

    public static List<Address> getAddresss() {
        Document document = null;
        List<Address> addressList = new ArrayList<>(100);

        try {
            document = Jsoup.connect("http://www.ip33.com/area_code.html" ).get();
        } catch (IOException e) {
            logger.error("连接失败");
        }
        Elements divs = document.getElementsByClass("ip");
        divs.forEach(div -> {

            Elements elements = div.child(1).child(0).child(1).children();
                elements.forEach(element -> {
                Address address = new Address();
                StringBuilder province = new StringBuilder("");
                String country = div.child(1).child(0).child(0).text();
                int c = country.indexOf(" ");
                int p = div.child(0).text().indexOf(" ");
                int e = element.text().indexOf(" ");
                province.append(div.child(0).text().substring(0, p)).append(country.substring(0,c)).append(element.text().substring(0, e));
                address.setAddress(province.toString());
                addressList.add(address);
            });

        });

        return addressList;
    }

    /*爬取文章信息*/
    public static List<Article> getArticles() {
        Document document = null;
        List<Article> articleList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/c/0b6ad2de4b21?order_by=top&count=50&page=" +
                        "ource=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("have-img");
            divs.forEach(div -> {
                String articleUrl = div.child(0).attr("href");
                Document document1 = null;
                try {
                    document1 = Jsoup.connect("https://www.jianshu.com" + articleUrl).get();
                } catch (IOException e) {
                    logger.error("文章获取失败");
                }
                Element articleElement = document1.getElementsByClass("_2rhmJa").first();
                Article article = new Article();
                article.setContent(articleElement.html());
                Elements elements = div.children();
                Element linkElement = elements.get(0);
                Element divElement = elements.get(1);
                article.setAuthorId(DataUtil.getAuthorId());
                article.setTitle(divElement.child(0).text());
                article.setForwardAccount(DataUtil.getForwordAccount());
                article.setDescription(divElement.child(1).text());
                String img = "https:" + linkElement.child(0).attr("src");
                int index = img.indexOf("?");
                article.setAvatar(img.substring(0, index));
                Elements metaChildren = divElement.child(2).children();
                String comments = metaChildren.get(2).text();
                String likes = metaChildren.last().text();
                try {
                    article.setCommentAccount(Integer.parseInt(comments));
                    article.setLikeAccount(Integer.parseInt(likes));
                } catch (NumberFormatException e) {
                    logger.error("格式转换异常");
                }
                article.setCreateTime(LocalDateTime.now());
                articleList.add(article);
            });
        }
        System.out.println(articleList.size());
        return articleList;
    }

}