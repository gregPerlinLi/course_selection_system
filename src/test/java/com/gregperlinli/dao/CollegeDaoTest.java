package com.gregperlinli.dao;

import com.gregperlinli.dao.impl.CollegeDaoImpl;
import com.gregperlinli.pojo.College;
import com.gregperlinli.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对<code>CollegeDao</code>进行单元测试
 *
 * @author gregperlinli
 */
public class CollegeDaoTest {
    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            CollegeDao collegeDao = new CollegeDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            College college = new College(1, "物理与光电工程学院");
            collegeDao.insert(conn, college);
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
            CollegeDao collegeDao = new CollegeDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            College college = new College(1, "I don't know");
            collegeDao.updateById(conn, college);
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
            CollegeDao collegeDao = new CollegeDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            collegeDao.deleteById(conn, 1);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }


    /**
     * 测试<code>getCollegeById()</code>方法
     */
    @Test
    public void testGetById() {
        Connection conn = null;
        try{
            CollegeDao collegeDao = new CollegeDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            College college = collegeDao.getCollegeById(conn, 2);
            System.out.println(college);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getCollegeByCollegeName()</code>方法
     */
    @Test
    public void testGetByCollegeNam() {
        Connection conn = null;
        try{
            CollegeDao collegeDao = new CollegeDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            College college = collegeDao.getCollegeByCollegeName(conn, "物理与光电工程学院");
            System.out.println(college);
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
            CollegeDao collegeDao = new CollegeDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            List<College> colleges = collegeDao.getAll(conn);
            System.out.println(colleges.toString());
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
            CollegeDao collegeDao = new CollegeDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            Long count = collegeDao.getCount(conn);
            System.out.println(count);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

}
