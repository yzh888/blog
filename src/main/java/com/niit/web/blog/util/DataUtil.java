package com.niit.web.blog.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author zh_yan
 * @ClassName DataUtil
 * @Description 数据生成工具，用来生成用户的一些数据
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class DataUtil {
    /**
    *获得电话号码
     *
     * @return
     */



    public static  String getMobile(){
        StringBuilder stringBuilder=new StringBuilder("139");
        Random random=new Random();
        for (int i=0;i<8;i++){
            int num=random.nextInt(10);
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    public static String getPassword(){
        StringBuilder password=new StringBuilder("139");
        Random random1=new Random();
        for (int i=0;i<6;i++){
            int num1=random1.nextInt(10);
            password.append(num1);
        }
        return DigestUtils.md5Hex(password.toString());
    }
    public static String getGender(){
        String [] genders=new String[]{"n","n"};
        Random random=new Random();
        int index=random.nextInt(2);
        return genders[index];
    }
    public static LocalDate getBirthday(){
        LocalDate now= LocalDate.now();
        Random random=new Random();
        int bound=random.nextInt(8888);
        return now.minusDays(bound);
    }

 /*   public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.println(DataUtil.getMobile());
        }
        for (int i=0;i<6;i++){
            System.out.println(DataUtil.getPassword());
        }
        for (int i=0;i<10;i++){
            System.out.println(DataUtil.getBirthday());
        }
    }*/

}
