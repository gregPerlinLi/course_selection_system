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
 * @author gregperlinli
 * @see LoginService
 * @since 2021-7-26
 */
public class LoginServiceImpl implements LoginService {
    Connection conn = null;

    @Override
    public boolean studentLogin(String username, String password) {
        final StudentDao studentDao = new StudentDaoImpl();
        Student student = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            student = studentDao.getStuByUsername(conn, username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        if ( student != null) {
            return student.getPassword().equals(password);
        } else {
            return false;
        }
    }


    @Override
    public boolean adminLogin(String username, String password) {
        final AdminDao adminDao = new AdminDaoImpl();
        Admin admin = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            admin = adminDao.getAdmByUsername(conn, username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        if ( admin != null) {
            return admin.getPassword().equals(password);
        } else {
            return false;
        }
    }
}
