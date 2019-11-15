package com.niit.web.blog.factory;

import com.niit.web.blog.dao.*;
import com.niit.web.blog.dao.JianshuDao;
import com.niit.web.blog.dao.impl.*;
import com.niit.web.blog.dao.impl.JianshuDaoImpl;
import com.niit.web.blog.entity.Student;
import com.niit.web.blog.entity.Jianshu;

/**
 * @author zh_yan
 * @ClassName DaoFactory
 * @Description TODO
 * @Date 2019/11/5
 * @Version 1.0
 **/
public class DaoFactory {
    public static StudentDao getStudentDaoInstance(){
        return new StudentDaoImpl();
    }
    public static UserDao getUserDaoInstance(){
        return new UserDaoImpl();
    }
    public static JianshuDao getJianshuDaoInstance(){ return new JianshuDaoImpl();}
    public static AddressDao getAddressDaoInstance(){ return new AddressDaoImpl();}
}
