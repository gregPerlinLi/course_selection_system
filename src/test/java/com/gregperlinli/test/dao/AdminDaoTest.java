package com.gregperlinli.test.dao;

import com.gregperlinli.dao.AdminDao;
import com.gregperlinli.dao.impl.AdminDaoImpl;
import com.gregperlinli.pojo.Admin;
import com.gregperlinli.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对<code>AdminDao</code>进行单元测试
 *
 * @author gregperlinli
 */
public class AdminDaoTest {
    final private AdminDao adminDao = new AdminDaoImpl();

    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void insert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            Admin admin = new Admin(0, "gregperlinli", "123456");
            adminDao.insert(conn, admin);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>updateById()</code>方法
     */
    @Test
    public void updateById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Admin admin = new Admin(3, "test1", "111111");
            adminDao.updateById(conn, admin);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>deleteById()</code>方法
     */
    @Test
    public void deleteById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            adminDao.deleteById(conn, 3);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }


    /**
     * 测试<code>getAdmById()</code>方法
     */
    @Test
    public void getAdmById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Admin admin = adminDao.getAdmById(conn, 4);
            System.out.println(admin);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getAdmByUsername()</code>方法
     */
    @Test
    public void getAdmByUsername() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Admin admin = adminDao.getAdmByUsername(conn, "gregperlinli");
            System.out.println(admin);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getAll()</code>方法
     */
    @Test
    public void getAll() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            List<Admin> admins = adminDao.getAll(conn);
            System.out.println(admins.toString());
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getCount()</code>方法
     */
    @Test
    public void getCount() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Long count = adminDao.getCount(conn);
            System.out.println(count);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
}
