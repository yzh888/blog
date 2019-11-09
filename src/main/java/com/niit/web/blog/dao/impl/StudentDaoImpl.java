package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.StudentDao;
import com.niit.web.blog.entity.Student;
import com.niit.web.blog.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @ClassName StudentDaoImpl
 * @Description TODO
 * @Date 2019/11/5:15:22
 * @Version 1.0
 **/
public class StudentDaoImpl implements StudentDao {
    private QueryRunner runner = new QueryRunner();
    @Override
    public List<Student> selectAll() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection connection = DBUtils.getConnection();
        String sql = "INSERT INTO t_user (mobile,password,nickname,avatar,gender,birthday,introduction,create_time) VALUES (?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql) ;
        ResultSet rs = pstmt. executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student. setId(rs.getInt("id"));
            student. setUsername (rs.getString("username"));
            student. setAvatar(rs.getString("avatar"));
            Timestamp timestamp = rs.getTimestamp("create_time");
            student.setCreateTime (timestamp.toLocalDateTime());
            studentList.add(student);
        }
        return studentList ;
    }
    @Override
    public int[] batchInsert(List<Student> studentList) throws SQLException {
        Connection connection = DBUtils.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_student VALUES (NULL,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        studentList.forEach(student -> {
            try {
                pstmt.setString(1,student.getUsername());
                pstmt.setString(2,student.getAvatar());
                pstmt.setTimestamp(3, Timestamp.valueOf(student.getCreateTime()));
                pstmt.addBatch();
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        int[] n = pstmt.executeBatch();
        connection.commit();
        pstmt.close();
        connection.close();
        return n;
    }



}
