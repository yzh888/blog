package com.niit.web.blog.util;

import com.aliyun.oss.OSSClient;

import java.io.File;
import java.sql.SQLOutput;
import java.util.UUID;

/**
 * @author zh_yan
 * @ClassName AliOSSUtil
 * @Description TODO
 * @Date 2019/11/6
 * @Version 1.0
 **/
public class AliOSSUtil {
    public static String ossUpload(File file){
        String bucketDomain = "https://niit-soft.oss-cn-hangzhou.aliyuncs.com/";
        String endpoint ="oss-cn-hangzhou.aliyuncs.com";
        String accessKeyID="LTAI4FfRRtnpAC84xMh1pTfT";
        String accessKeySecret="vh28qKMfqs1v1E2ecofUAnn0sr8svx";
        String bucketName="niit-soft";
        String filedir="avatar/";
        String fileName=file.getName();
        System.out.println(fileName);
        String fileKey="yanzihao"+fileName.substring(fileName.indexOf("."));
        OSSClient ossClient=new OSSClient(endpoint,accessKeyID,accessKeySecret);
        ossClient.putObject(bucketName,filedir+fileKey,file);
        StringBuffer url=new StringBuffer();
        url.append(bucketDomain).append(filedir).append(fileKey);
        ossClient.shutdown();
        return url.toString();
    }
public static void main(String[] args){
        File file=new File("E:/fj.jpg");
        String url=ossUpload(file);
    System.out.println(url);
}
}
