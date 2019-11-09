package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.util.DBUtils;
import com.niit.web.blog.util.DbUtil;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Date 2019/11/9:14:40
 * @Version 1.0
 **/
public class UserDaoImpl implements UserDao {

    @Override

    public int insert(User user) throws SQLException {

        Connection connection = DbUtil.getConnection();

        String sql = "INSERT INTO t_user (mobile,password) VALUES (?,?) ";

        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, user.getMobile());

        pstmt.setString(2, user.getPassword());

        int n = pstmt.executeUpdate();

        DbUtil.close(null, pstmt, connection);

        return n;

    }



    @Override

    public int[] batchInsert(List<User> userList) throws SQLException {

        Connection connection = DbUtil.getConnection();

        String sql = "INSERT INTO t_user (mobile,password,nickname,avatar,gender,birthday,introduction,create_time) VALUES (?,?,?,?,?,?,?,?) ";

        PreparedStatement pstmt = connection.prepareStatement(sql);

        connection.setAutoCommit(false);

        userList.forEach(user -> {

            try {

                pstmt.setString(1, user.getMobile());

                pstmt.setString(2, user.getPassword());

                pstmt.setString(3, user.getNickname());

                pstmt.setString(4, user.getAvatar());

                pstmt.setString(5, user.getGender());

                pstmt.setObject(6, user.getBirthday());

                pstmt.setString(7, user.getIntroduction());

                pstmt.setObject(8, user.getCreateTime());

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

    public User findUserByMobile(String mobile) throws SQLException {

        Connection connection = DbUtil.getConnection();

        String sql = "SELECT * FROM t_user WHERE mobile = ? ";

        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, mobile);

        ResultSet rs = pstmt.executeQuery();

        User user = null;

        if (rs.next()) {

            user = new User();

            user.setId(rs.getLong("id"));

            user.setMobile(rs.getString("mobile"));

            user.setPassword(rs.getString("password"));

            user.setNickname(rs.getString("nickname"));

            user.setAvatar(rs.getString("avatar"));

            user.setGender(rs.getString("gender"));

            user.setBirthday(rs.getDate("birthday").toLocalDate());

            user.setIntroduction(rs.getString("introduction"));

            user.setAddress(rs.getString("address"));

            user.setFollows(rs.getShort("follows"));

            user.setFans(rs.getShort("fans"));

            user.setArticles(rs.getShort("articles"));

            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());

            user.setStatus(rs.getShort("status"));

        }

        return user;

    }

}