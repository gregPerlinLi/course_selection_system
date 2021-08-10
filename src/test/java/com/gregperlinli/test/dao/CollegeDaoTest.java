package com.gregperlinli.test.dao;

import com.gregperlinli.dao.CollegeDao;
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
    final private CollegeDao collegeDao = new CollegeDaoImpl();

    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void insert() {
        Connection conn = null;
        try {
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
    public void updateById() {
        Connection conn = null;
        try{
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
    public void deleteById() {
        Connection conn = null;
        try{
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
    public void getCollegeById() {
        Connection conn = null;
        try{
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
    public void getCollegeByCollegeName() {
        Connection conn = null;
        try{
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
    public void getAllgetAll() {
        Connection conn = null;
        try{
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
    public void getCount() {
        Connection conn = null;
        try{
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
