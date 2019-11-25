package com.niit.web.blog.dao;
import com.niit.web.blog.entity.Jianshu;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zh_yan
 * @ClassName Jianshu
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public interface JianshuDao {

    int insert(Jianshu jianshu) throws SQLException;

    /**
     * 批量新增用户
     *
     * @param jianshuList
     * @return int[]
     * @throws SQLException
     */
    int[] batchInsert(List<Jianshu> jianshuList)throws SQLException;

    /**

     * 根据手机号查找用户

     * @param mobile

     * @return

     * @throws SQLException

     */

    Jianshu findJianshuByMobile(String mobile) throws SQLException;
}
