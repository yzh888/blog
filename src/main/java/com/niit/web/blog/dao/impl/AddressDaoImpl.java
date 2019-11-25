package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.AddressDao;
import com.niit.web.blog.entity.Address;
import com.niit.web.blog.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zh_yan
 * @ClassName AddressDaoImpl
 * @Description TODO
 * @Date 2019/11/13
 * @Version 1.0
 **/
public class AddressDaoImpl implements AddressDao {
    @Override

    public int[] batchInsert(List<Address> addressList) throws SQLException {

        Connection connection = DbUtil.getConnection();

        String sql = "INSERT INTO t_address (address,city) VALUES (?,?) ";

        PreparedStatement pstmt = connection.prepareStatement(sql);

        connection.setAutoCommit(false);

        addressList.forEach(address -> {

            try {

                pstmt.setString(1, address.getAddress());
                pstmt.setString(2, address.getCity());
                pstmt.addBatch();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        });

        //执行批处理操作

        int[] result = pstmt.executeBatch();

        connection.commit();

        DbUtil.close(null, pstmt, connection);

        return result;

    }
}
