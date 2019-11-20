package com.niit.web.blog.test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author jh_wu
 * @ClassName Code
 * @Description TODO
 * @Date 2019/11/19:14:04
 * @Version 1.0
 **/
public class StringUtil {
    public static String getcode(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder("");
        int n = 0 ;

        for (int i = 0; i < 3; i++) {
            final int temp = random.nextInt(26) + 97;
            String s = String.valueOf((char) temp);
           for (int j=0;j<10;j++){
                n = (int)(Math.random()*3);}
            stringBuilder.append(s).append(n);
        }

        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time1 =df.format(new Date());
        System.out.println(time1);
        File dir = new File("D:/Web/"+time1);
        dir.mkdirs();

        File file1 = new File("D:/code.jpg");
        File file2 = new File("D:/Web/"+time1+"/UUID.jpg");

        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] bytes = new byte[10];
        int len = 0;
        try {
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);

            try {
                while ((len = fis.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
