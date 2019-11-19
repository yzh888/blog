package com.niit.web.blog.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author zh_yan
 * @ClassName CreateFile
 * @Description TODO
 * @Date 2019/11/19
 * @Version 1.0
 **/
public class CreateFile {
    public static boolean createFile() {
        LocalDate date = LocalDate.now();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



        String path = "D:/"+date;
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }else {
            System.out.println("文件夹已存在");
        }


        File file1 = new File(path, UUID.randomUUID() + ".text");
        try {
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;


    }

    public static void main(String[] args) {
        System.out.println(createFile());
    }
}
