package com.niit.web.blog.domain.Vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zh_yan
 * @ClassName ArticleVo
 * @Description TODO
 * @Date 2019/11/15
 * @Version 1.0
 **/
@Data
public class ArticleVo {
    private Long id;
    private Long authorId;
    private String nickname;
    private String avatar;
    private String title;
    private String description;
    private String content;
    private Integer commentAccount;
    private Integer likeAccount;
    private Integer forwardAccount;
    private LocalDateTime createTime;
}
