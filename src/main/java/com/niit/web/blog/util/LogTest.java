package com.niit.web.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zh_yan
 * @ClassName LogTest
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class LogTest {
    private static Logger logger= LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.info("hello");
    }

}
