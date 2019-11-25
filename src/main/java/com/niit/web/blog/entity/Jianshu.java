package com.niit.web.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zh_yan
 * @ClassName Jianshu
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
@Data
public class Jianshu {
    private Long id;
    private String mobile;
    private String password;
    private String writer;
    private String title;
    private String text;
    private String avatar;
    private Short message;
    private Short attention;
    private LocalDateTime create_time;

}
