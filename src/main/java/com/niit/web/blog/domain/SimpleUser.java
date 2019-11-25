package com.niit.web.blog.domain;

import lombok.Data;

/**
 * @author zh_yan
 * @ClassName SimpleUser
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
@Data
public class SimpleUser {
    private Long id;
    private String nickname;
    private String avatar;
}