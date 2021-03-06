package com.gregperlinli.service.impl;

import com.gregperlinli.dao.AdminDao;
import com.gregperlinli.dao.StudentDao;
import com.gregperlinli.dao.impl.AdminDaoImpl;
import com.gregperlinli.dao.impl.StudentDaoImpl;
import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.LoginService;
import com.gregperlinli.utils.JDBCUtils;

import java.sql.Connection;

/**
 * <code>LoginService</code>的实现类
 *
 * @author gregperlinli
 * @see LoginService
 * @since 2021-7-26
 */
public class LoginServiceImpl implements LoginService {
    final private StudentDao studentDao = new StudentDaoImpl();
    final private AdminDao adminDao = new AdminDaoImpl();
    private Connection conn = null;

    @Override
    public Student studentLogin(String username, String password) {
        Student student = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            student = studentDao.getStuByUsername(conn, username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if ( student != null && student.getPassword().equals(password)) {
            return student;
        } else {
            return null;
        }
    }


    @Override
    public Admin adminLogin(String username, String password) {
        Admin admin = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            admin = adminDao.getAdmByUsername(conn, username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if ( admin != null && admin.getPassword().equals(password)) {
            return admin;
        } else {
            return null;
        }
    }
}
