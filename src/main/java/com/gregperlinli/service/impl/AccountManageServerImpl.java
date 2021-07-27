package com.gregperlinli.service.impl;

import com.gregperlinli.dao.AdminDao;
import com.gregperlinli.dao.StudentDao;
import com.gregperlinli.dao.impl.AdminDaoImpl;
import com.gregperlinli.dao.impl.StudentDaoImpl;
import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.AccountManageServer;
import com.gregperlinli.utils.JDBCUtils;

import java.sql.Connection;

public class AccountManageServerImpl implements AccountManageServer {
    Connection conn = null;

    @Override
    public boolean studentRegist(Student student) {
        final StudentDao STUDENT_DAO = new StudentDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( STUDENT_DAO.getStuByUsername(conn, student.getUsername()) == null && STUDENT_DAO.getStuByStuNum(conn, student.getStuNum()) == null) {
                STUDENT_DAO.insert(conn, student);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }

    @Override
    public boolean adminRegist(Admin admin) {
        final AdminDao ADMIN_DAO = new AdminDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( ADMIN_DAO.getAdmByUsername(conn, admin.getUsername()) == null) {
                ADMIN_DAO.insert(conn, admin);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }

    @Override
    public boolean studentUpdate(Student student) {
        final StudentDao STUDENT_DAO = new StudentDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( STUDENT_DAO.getStuById(conn, student.getId()) != null ) {
                STUDENT_DAO.updateById(conn, student);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }

    @Override
    public boolean adminUpdate(Admin admin) {
        final AdminDao ADMIN_DAO = new AdminDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( ADMIN_DAO.getAdmById(conn, admin.getId()) != null ) {
                ADMIN_DAO.updateById(conn, admin);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }
}
