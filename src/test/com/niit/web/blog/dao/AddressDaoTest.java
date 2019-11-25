package com.niit.web.blog.dao;

import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AddressDaoTest {


        private static Logger logger= LoggerFactory.getLogger(AddressDaoTest.class);
        private AddressDao addressDao= DaoFactory.getAddressDaoInstance();
        @Test
        public void batchInsert() {
            try {
                int[] result=addressDao.batchInsert(JSoupSpider.getAddresss());
                if (result.length!=0){
                    logger.info("成功新增" + result.length + "个地址");
                }
            } catch (SQLException e) {
                logger.error("批量新增地址异常");
            }
        }
    }