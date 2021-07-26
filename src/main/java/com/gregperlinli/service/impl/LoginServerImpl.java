package com.gregperlinli.service.impl;

import com.gregperlinli.dao.AdminDao;
import com.gregperlinli.dao.StudentDao;
import com.gregperlinli.dao.impl.AdminDaoImpl;
import com.gregperlinli.dao.impl.StudentDaoImpl;
import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.LoginServer;
import com.gregperlinli.utils.JDBCUtils;

import java.sql.Connection;

public class LoginServerImpl implements LoginServer {
    Connection conn = null;

    @Override
    public boolean studentLogin(String username, String password) {
        final StudentDao STUDENT_DAO = new StudentDaoImpl();
        Student student = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            student = STUDENT_DAO.getStuByUsername(conn, username);
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
        final AdminDao ADMIN_DAO = new AdminDaoImpl();
        Admin admin = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            admin = ADMIN_DAO.getAdmByUsername(conn, username);
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
