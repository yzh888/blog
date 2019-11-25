package com.niit.web.blog.domain.Dto;

import lombok.Data;

/**
 * @author zh_yan
 * @ClassName AddressDto
 * @Description TODO
 * @Date 2019/11/13
 * @Version 1.0
 **/
@Data
public class AddressDto {
    private String mobile;
    private String password;


    public  AddressDto(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public  AddressDto() {
    }



}

