package com.niit.web.blog.domain.Dto;

import lombok.Data;

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
    private String code;


   /* public UserDto(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;

    }

    public UserDto() {
    }
*/


}
