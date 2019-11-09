package com.niit.web.blog.domain;

import lombok.Data;

import java.security.PrivateKey;
import java.sql.PreparedStatement;

/**
 * @author zh_yan
 * @ClassName UserDto
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
@Data
public class UserDto {
    private String mobile;
    private String password;


    public UserDto(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public UserDto() {
    }



}
