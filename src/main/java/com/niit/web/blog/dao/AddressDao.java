package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zh_yan
 * @ClassName AddressDao
 * @Description TODO
 * @Date 2019/11/13
 * @Version 1.0
 **/
public interface AddressDao {


    /**
     * 批量新增用户
     *
     * @param addressList
     * @return int[]
     * @throws SQLException
     */
    int[] batchInsert(List<Address> addressList)throws SQLException;
}
