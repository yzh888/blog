package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @author
 * @ClassName StudentDao
 * @Description TODO
 * @Date 2019/11/5
 * @Version 1.0
 **/
public interface StudentDao {
    List<Student> selectAll()throws SQLException;


    /**
     * 批量插入学生
     * @param studentList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<Student> studentList)throws SQLException;



}
