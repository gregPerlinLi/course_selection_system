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
    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            AdminDao adminDao = new AdminDaoImpl();
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
    public void testUpdate() {
        Connection conn = null;
        try{
            AdminDao adminDao = new AdminDaoImpl();
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
    public void testDelete() {
        Connection conn = null;
        try{
            AdminDao adminDao = new AdminDaoImpl();
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
    public void testGetById() {
        Connection conn = null;
        try{
            AdminDao adminDao = new AdminDaoImpl();
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
    public void testGetByUsername() {
        Connection conn = null;
        try{
            AdminDao adminDao = new AdminDaoImpl();
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
    public void testGetAll() {
        Connection conn = null;
        try{
            AdminDao adminDao = new AdminDaoImpl();
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
    public void testGetCount() {
        Connection conn = null;
        try{
            AdminDao adminDao = new AdminDaoImpl();
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
