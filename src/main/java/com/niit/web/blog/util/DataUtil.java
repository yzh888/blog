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
        String [] genders=new String[]{"男","女"};
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
    public static String getTitle(){
        StringBuilder title=new StringBuilder("139");
        Random random1=new Random();
        for (int i=0;i<6;i++){
            int num1=random1.nextInt(10);
            title.append(num1);
        }
        return DigestUtils.md5Hex(title.toString());
    }
    public static String getText(){
        StringBuilder title=new StringBuilder("139");
        Random random1=new Random();
        for (int i=0;i<6;i++){
            int num1=random1.nextInt(10);
            title.append(num1);
        }
        return DigestUtils.md5Hex(title.toString());
    }
    public static Long getUserId(){
        Random random = new Random();
        long bound = random.nextInt(61);
        return bound;
    }
    public static LocalDateTime getCreateTime(){
        LocalDateTime now = LocalDateTime.now();
        Random random = new Random();
        int bound = random.nextInt(999);
        return now.minusHours(bound);
    }

    public static Short getMessage(){
        Random random=new Random();
        short num=(short)random.nextInt(100);
        return num;
    }
    public static Short getAttention(){
        Random random=new Random();
        short num1=(short)random.nextInt(100);
        return num1;
    }
    /**
     * 随机获取作者id
     * @return
     */
    public static long getAuthorId (){
        Random random = new Random();
        long id = random.nextInt(73);
        return id;
    }

    /**
     * 随机生成转发人数
     * @return
     */
    public static int getForwordAccount (){
        Random random = new Random();
        int account = random.nextInt(200);
        return account;
    }

    /**
     * 随机生成评论人数
     * @return
     */
    public static int getCommentAccount (){
        Random random = new Random();
        int account = random.nextInt(500);
        return account;
    }

    /**
     * 随机生成关注量
     * @return
     */
    public static int getLikeAccount (){
        Random random = new Random();
        int account = random.nextInt(500);
        return account;
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
