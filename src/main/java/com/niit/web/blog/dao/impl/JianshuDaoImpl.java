package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.JianshuDao;
import com.niit.web.blog.entity.Jianshu;
import com.niit.web.blog.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zh_yan
 * @ClassName JianshuDaoImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class JianshuDaoImpl implements JianshuDao {

    @Override

    public int insert(Jianshu jianshu) throws SQLException {

        Connection connection = DbUtil.getConnection();

        String sql = "INSERT INTO t_user (mobile,password) VALUES (?,?) ";

        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, jianshu.getMobile());

        pstmt.setString(2, jianshu.getPassword());

        int n = pstmt.executeUpdate();

        DbUtil.close(null, pstmt, connection);

        return n;

    }



    @Override

    public int[] batchInsert(List<Jianshu> jianshuList) throws SQLException {

        Connection connection = DbUtil.getConnection();

        String sql = "INSERT INTO t_jianshu (mobile,password,writer,title,text,avatar,message,attention,create_time) VALUES (?,?,?,?,?,?,?,?,?) ";

        PreparedStatement pstmt = connection.prepareStatement(sql);

        connection.setAutoCommit(false);

        jianshuList.forEach(jianshu -> {

            try {

                pstmt.setString(1, jianshu.getMobile());

                pstmt.setString(2, jianshu.getPassword());

                pstmt.setString(3, jianshu.getWriter());
                pstmt.setString(4, jianshu.getTitle());
                pstmt.setString(5, jianshu.getText());

                pstmt.setString(6, jianshu.getAvatar());

                pstmt.setObject(7, jianshu.getMessage());

                pstmt.setObject(8, jianshu.getAttention());



                pstmt.setObject(9, jianshu.getCreate_time());

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



    @Override

    public Jianshu findJianshuByMobile(String mobile) throws SQLException {

        Connection connection = DbUtil.getConnection();

        String sql = "SELECT * FROM t_jianshu WHERE mobile = ? ";

        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, mobile);

        ResultSet rs = pstmt.executeQuery();

        Jianshu jianshu = null;

        if (rs.next()) {

            jianshu = new Jianshu();

            jianshu.setId(rs.getLong("id"));

            jianshu.setMobile(rs.getString("mobile"));

            jianshu.setPassword(rs.getString("password"));

            jianshu.setWriter(rs.getString("writer"));
            jianshu.setTitle(rs.getString("title"));
            jianshu.setText(rs.getString("text"));

            jianshu.setAvatar(rs.getString("avatar"));

            jianshu.setMessage(rs.getShort("message"));
            jianshu.setMessage(rs.getShort("attention"));


            jianshu.setCreate_time(rs.getTimestamp("create_time").toLocalDateTime());



        }

        return jianshu;

    }

}